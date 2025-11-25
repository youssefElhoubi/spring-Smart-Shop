package com.smartshop.entity;
import com.smartshop.enums.CustomerTier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-One mapping to the User entity for authentication data
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Maps the Client's primary key (id) to the User's primary key
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CustomerTier tier = CustomerTier.BASIC;

    @Column(name = "total_orders", nullable = false)
    @Builder.Default
    private Integer totalOrders = 0;

    @Column(name = "total_spent", nullable = false)
    @Builder.Default
    private Double totalSpent = 0.0;
}
