package com.example.elcstore.repository;

import com.example.elcstore.domain.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductDetailRepository extends JpaRepository<OrderProductDetail, UUID> {
}
