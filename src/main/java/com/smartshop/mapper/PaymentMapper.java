package com.smartshop.mapper;

import com.smartshop.dto.payment.PaymentRequestDTO;
import com.smartshop.dto.payment.PaymentResponseDTO;
import com.smartshop.dto.payment.PaymentUpdateDTO;
import com.smartshop.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDTO toResponse(Payment payment) {
        if (payment == null) {
            return null;
        }

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .paymentDate(payment.getPaymentDate())
                .orderId(payment.getOrder() != null ? payment.getOrder().getId() : null)
                .build();
    }

    public Payment toEntity(PaymentRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setMethod(requestDTO.getMethod());

        return payment;
    }

    public void updateEntityFromDto(PaymentUpdateDTO updateDTO, Payment payment) {
        if (updateDTO == null || payment == null) {
            return;
        }

        if (updateDTO.getAmount() != null) {
            payment.setAmount(updateDTO.getAmount());
        }
        if (updateDTO.getPaymentDate() != null) {
            payment.setPaymentDate(updateDTO.getPaymentDate());
        }
    }
}