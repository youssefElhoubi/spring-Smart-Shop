package com.smartshop.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Client ID is required to place an order.")
    private Long clientId;


    @NotEmpty(message = "Order must contain at least one item.")
    @Valid
    private List<OrderItemRequestDTO> items;
    
    private String promoCode;
}