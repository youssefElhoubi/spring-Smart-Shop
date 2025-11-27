package com.smartshop.service;

import com.smartshop.dto.payment.PaymentRequestDTO;
import com.smartshop.dto.payment.PaymentResponseDTO;
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

    public PaymentResponseDTO addPayment(PaymentRequestDTO dto){

        return null;
    }
}
