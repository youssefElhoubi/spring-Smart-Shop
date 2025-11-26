package com.smartshop.dto.order;

import com.smartshop.enums.OrderStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private Long clientId;
    private LocalDate date;

    // Financials
    private Double subTotal;
    private Double discountAmount;
    private Double tax;
    private Double total;
    private Double remainingAmount;
    private String promoCode;

    // Status
    private OrderStatus status;

    // Detail lines are often returned with the order
    private List<OrderItemResponseDTO> items; // Assuming you will create this DTO later
}