package com.smartshop.service;

import com.smartshop.component.EntityValidator;
import com.smartshop.dto.order.OrderRequestDTO;
import com.smartshop.dto.order.OrderResponseDTO;
import com.smartshop.dto.order.OrderUpdateDTO;
import com.smartshop.entity.Client;
import com.smartshop.entity.Order;
import com.smartshop.entity.OrderItem;
import com.smartshop.entity.Product;
import com.smartshop.enums.CustomerTier;
import com.smartshop.enums.Role;
import com.smartshop.exeptions.BusinessRuleViolationException;
import com.smartshop.exeptions.ResourceNotFoundException;
import com.smartshop.mapper.OrderMapper;
import com.smartshop.repository.ClientRepository;
import com.smartshop.repository.OrderItemRepository;
import com.smartshop.repository.OrderRepository;
import com.smartshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;
    private final EntityValidator entityValidator;


    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, ClientRepository clientRepository, OrderMapper orderMapper, EntityValidator entityValidator) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderMapper = orderMapper;
        this.entityValidator = entityValidator;
    }

    public OrderResponseDTO create(OrderRequestDTO dto) {

        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() ->
                new ResourceNotFoundException("Client with Id " + dto.getClientId() + " not found")
        );

        List<Long> productIds = dto.getItems().stream()
                .map(p -> p.getProductId())
                .toList();

        entityValidator.ensureAllExist(
                productIds,
                productRepository,
                "Product",
                Product::getId
        );
        Order order = orderMapper.toNewEntity(client, dto);
        Order finalOrder = orderRepository.save(order);
        entityValidator.ensureQuantity(dto.getItems());

        List<OrderItem> orderItems = dto.getItems().stream().map((o) -> {
            Product product = productRepository.findById(o.getProductId()).orElseThrow();
            return OrderItem.builder().product(product)
                    .quantity(o.getQuantity())
                    .unitPriceAtSale(product.getPrice())
                    .order(finalOrder)
                    .lineTotalHT(product.getPrice() * o.getQuantity())
                    .build();
        }).toList();

        orderItemRepository.saveAll(orderItems);

        Double subTotal = orderItems.stream().mapToDouble(OrderItem::getLineTotalHT).sum();
        order.setSubTotal(subTotal);

        Double totalDiscountPercentage = 0.0;

        if (order.getClient().getTier() == CustomerTier.PLATINUM && subTotal >= 1200) {
            totalDiscountPercentage += 15.0; // Platinum
        } else if (subTotal >= 800 && order.getClient().getTier() == CustomerTier.GOLD) {
            totalDiscountPercentage += 10.0; // Gold
        } else if (subTotal >= 500 && order.getClient().getTier() == CustomerTier.SILVER) {
            totalDiscountPercentage += 5.0;  // Silver
        }


        if (dto.getPromoCode() != null) {
            totalDiscountPercentage += 5.0;
        }

        Double discountAmount = subTotal * (totalDiscountPercentage / 100);
        order.setDiscountAmount(discountAmount);
        Double amountSubjectToTax = subTotal - discountAmount;

        Double taxPercentage = order.getTax();
        Double taxValue = amountSubjectToTax * (taxPercentage / 100);

        Double total = amountSubjectToTax + taxValue;

        order.setTotal(total);
        order.setRemainingAmount(total);

        order = orderRepository.save(order);

        return orderMapper.toResponseDTO(order);
    }

    public OrderResponseDTO updateOrder(OrderUpdateDTO dto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("order with id " + id + "was not found ");
        });
        order.setStatus(dto.getStatus());
        orderRepository.save(order);
        return orderMapper.toResponseDTO(order);

    }
    public OrderResponseDTO findById( Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("order with id " + id + "was not found ");
        });
        return orderMapper.toResponseDTO(order);
    }
    public List<OrderResponseDTO> all(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponseDTO).toList();
    }
}
