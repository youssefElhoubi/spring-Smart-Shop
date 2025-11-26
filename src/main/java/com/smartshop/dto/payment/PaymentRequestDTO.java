package com.smartshop.dto.payment;

import com.smartshop.enums.PaymentMethod;

public class PaymentRequestDTO {
    private PaymentMethod method;
    private Long orderId;
}
