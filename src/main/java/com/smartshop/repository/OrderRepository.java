package com.smartshop.repository;

import com.smartshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByClient_Id(Long clientId);
}
