package com.smartshop.controller;

import com.smartshop.dto.payment.PaymentRequestDTO;
import com.smartshop.dto.payment.PaymentResponseDTO;
import com.smartshop.dto.payment.PaymentUpdateDTO;
import com.smartshop.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequestDTO request){
        PaymentResponseDTO response = paymentService.addPayment(request);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePayment(@Valid @RequestBody PaymentUpdateDTO request,@PathVariable Long id){
        PaymentResponseDTO response = paymentService.updatePayment(request,id);
        return ResponseEntity.ok(response);
    }
}
