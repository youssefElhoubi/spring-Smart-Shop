package com.smartshop.mapper;

import com.smartshop.dto.order.OrderRequestDTO;
import com.smartshop.dto.order.OrderResponseDTO;
import com.smartshop.dto.order.OrderUpdateDTO;
import com.smartshop.entity.Client;
import com.smartshop.entity.Order;
import com.smartshop.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderMapper {

    public Order toNewEntity(Client client, OrderRequestDTO dto) {
        if (client == null || dto == null) {
            return null;
        }

        return Order.builder()
                .client(client)
                .date(LocalDate.now())
                .status(OrderStatus.PENDING) // New orders always start PENDING
                .promoCode(dto.getPromoCode() != null ? dto.getPromoCode() : null)

                // Initialize calculated fields to 0.0 or null
                .subTotal(0.0)
                .discountAmount(0.0)
                .tax(0.0)
                .total(0.0)
                .remainingAmount(0.0) // Will be set to 'total' after calculation
                .build();
    }

    // --- 2. Mapping for Response (Entity -> DTO) ---
    public OrderResponseDTO toResponseDTO(Order order) {
        if (order == null) {
            return null;
        }

        // Note: You will need to fetch and map the List<OrderItem> separately
        // and set it on the DTO in the Service layer for a full response.

        return OrderResponseDTO.builder()
                .id(order.getId())
                .clientId(order.getClient() != null ? order.getClient().getId() : null)
                .date(order.getDate())
                .subTotal(order.getSubTotal())
                .discountAmount(order.getDiscountAmount())
                .tax(order.getTax())
                .total(order.getTotal())
                .remainingAmount(order.getRemainingAmount())
                .promoCode(order.getPromoCode())
                .status(order.getStatus())
                .build();
    }

    // --- 3. Mapping for Update (DTO -> Entity) ---
    /**
     * Updates an existing Order entity with data from the OrderUpdateDTO (e.g., status change).
     */
    public void updateEntityFromDTO(OrderUpdateDTO dto, Order order) {
        if (dto == null || order == null) {
            return;
        }

        // Only the status is mutable via this DTO
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }

        // Note: The service layer must handle the business rules around status transitions.
    }
}