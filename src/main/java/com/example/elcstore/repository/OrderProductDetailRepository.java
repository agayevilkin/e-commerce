package com.example.elcstore.repository;

import com.example.elcstore.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
