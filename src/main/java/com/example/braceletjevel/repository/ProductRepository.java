package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
