package com.smartshop.dto.payment;

import com.smartshop.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private Double amount;
    private PaymentMethod method;
    private Long orderId;
    private LocalDate paymentDate;
}