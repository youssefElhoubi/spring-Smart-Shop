package com.smartshop.repository;

import com.smartshop.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
    boolean existsByCode(String code);
}
