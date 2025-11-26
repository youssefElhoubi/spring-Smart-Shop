package com.smartshop.controller;

import com.smartshop.dto.order.OrderRequestDTO;
import com.smartshop.dto.order.OrderResponseDTO;
import com.smartshop.dto.order.OrderUpdateDTO;
import com.smartshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO dto) {
        OrderResponseDTO response = orderService.create(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("update/{id}")
    public ResponseEntity<OrderResponseDTO> update(@RequestBody @Valid OrderUpdateDTO dto, @PathVariable Long id) {
        OrderResponseDTO response = orderService.updateOrder(dto,id);
        return ResponseEntity.ok(response);
    }
}