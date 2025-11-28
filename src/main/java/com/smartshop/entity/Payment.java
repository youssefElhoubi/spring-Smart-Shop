package com.smartshop.entity;

import com.smartshop.enums.PaymentMethod;
import com.smartshop.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private Double amount;
    private PaymentMethod method;
    private PaymentStatus paymentStatus = PaymentStatus.EN_ATTENTE;
    private LocalDate paymentDate;
}
