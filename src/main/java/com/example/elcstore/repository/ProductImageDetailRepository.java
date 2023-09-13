package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductImageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageDetailRepository extends JpaRepository<ProductImageDetail, UUID> {
}
