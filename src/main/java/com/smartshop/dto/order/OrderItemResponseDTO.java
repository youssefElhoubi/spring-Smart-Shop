package com.smartshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {

    // Product Identification
    private Long productId;
    private String productName; // Useful to show name without fetching Product API separately

    // Purchase Details (Immutable data from the moment of purchase)
    private Integer quantity;
    private Double unitPriceAtSale;
    private Double lineTotalHT;     // quantity * unitPriceAtSale
}