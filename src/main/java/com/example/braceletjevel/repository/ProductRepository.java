package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByCategory_Name(String category);

    List<Product> findAllByCategory_NameAndBrand_Name(String category, String brand);

    List<Product> findAllByCreatedDateAfter(LocalDateTime dateTime);

    List<Product> findAllByDiscountIsNotNull();
}
