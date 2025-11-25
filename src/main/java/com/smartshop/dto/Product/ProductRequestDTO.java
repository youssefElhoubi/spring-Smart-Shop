package com.smartshop.dto.Product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required.")
    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters.")
    private String name;

    @NotNull(message = "Unit price is required.")
    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0.")
    private Double price;

    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0, message = "Stock quantity cannot be negative.")
    private Integer stock;
}
