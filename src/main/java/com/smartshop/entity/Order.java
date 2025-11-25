package com.smartshop.entity;

import com.smartshop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    private LocalDate date;
    private Double subTotal;
    private Double discountAmount;
    private Double tax;
    private Double total;
    private OrderStatus status;
    private Double remainingAmount;
    private String promoCode;
}
