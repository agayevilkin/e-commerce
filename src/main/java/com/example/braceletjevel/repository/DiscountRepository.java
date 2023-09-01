package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
}
