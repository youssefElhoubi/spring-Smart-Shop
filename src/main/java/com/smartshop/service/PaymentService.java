package com.smartshop.service;

import com.smartshop.dto.payment.PaymentRequestDTO;
import com.smartshop.dto.payment.PaymentResponseDTO;
import com.smartshop.dto.payment.PaymentUpdateDTO;
import com.smartshop.entity.Order;
import com.smartshop.entity.Payment;
import com.smartshop.enums.OrderStatus;
import com.smartshop.enums.PaymentMethod;
import com.smartshop.enums.PaymentStatus;
import com.smartshop.exeptions.BusinessRuleViolationException;
import com.smartshop.exeptions.ResourceNotFoundException;
import com.smartshop.mapper.PaymentMapper;
import com.smartshop.repository.OrderRepository;
import com.smartshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.orderRepository = orderRepository;
    }

    public PaymentResponseDTO addPayment(PaymentRequestDTO dto) {
        Payment payment = paymentMapper.toEntity(dto);
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("there is no order with ID" + dto.getOrderId());
        });

        if (dto.getMethod() == PaymentMethod.ESPECES && dto.getAmount() > 20000) {
            throw new BusinessRuleViolationException("this mount can not be processed as ESPECES");
        }
        payment.setOrder(order);
        if (order.getRemainingAmount() - payment.getAmount() < 0) {
            throw new BusinessRuleViolationException("payment amount is bigger than remaining amount  ");
        }
        order.setRemainingAmount(order.getRemainingAmount() - payment.getAmount());
        if (order.getRemainingAmount() == 0) {
            order.setStatus(OrderStatus.CONFIRMED);
            payment.setPaymentStatus(PaymentStatus.ENCAISSE);
        }
        orderRepository.save(order);
        paymentRepository.save(payment);
        return paymentMapper.toResponse(payment);
    }

    public PaymentResponseDTO updatePayment(PaymentUpdateDTO dto, Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("payment with id" + id + " was not found  ");
        });
        if (payment.getPaymentStatus() == PaymentStatus.ENCAISSE || payment.getPaymentStatus() == PaymentStatus.REJETE) {
            throw new ResourceNotFoundException("payment can not be processed");
        }
        Order order = orderRepository.findById(payment.getOrder().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("there is no order with ID" + payment.getOrder().getId());
        });
        if (order.getRemainingAmount() - dto.getAmount() < 0) {
            throw new BusinessRuleViolationException("payment amount exceeds the order remains  ");
        }
        payment.setAmount(payment.getAmount()+ dto.getAmount());
        order.setRemainingAmount(order.getRemainingAmount()- dto.getAmount() );
        orderRepository.save(order);
        paymentRepository.save(payment);

        return paymentMapper.toResponse(payment);
    }
}
