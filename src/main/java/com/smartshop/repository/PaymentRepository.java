package com.smartshop.repository;

import com.smartshop.entity.Order;
import com.smartshop.entity.Payment;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment getPaymentByOrder_Id(Long orderId);
}
