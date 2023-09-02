package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductDetailRepository extends JpaRepository<OrderProductDetail, UUID> {
}
