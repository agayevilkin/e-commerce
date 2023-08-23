package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
