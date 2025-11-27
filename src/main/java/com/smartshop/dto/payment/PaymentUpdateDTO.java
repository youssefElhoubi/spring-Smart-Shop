package com.smartshop.dto.payment;

import com.smartshop.enums.PaymentStatus;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateDTO {
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    private PaymentStatus paymentStatus;
}