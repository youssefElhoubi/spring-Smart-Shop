package com.smartshop.dto.order;

import com.smartshop.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO {
    @NotNull(message = "Status must be provided for order update.")
    private OrderStatus status;
}