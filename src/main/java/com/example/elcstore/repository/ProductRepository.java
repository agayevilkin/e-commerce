package com.example.elcstore.repository;

import com.example.elcstore.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAllByCategory_Name(String category, Pageable pageable);

    Page<Product> findAllByCategory_NameAndBrand_Name(String category, String brand, Pageable pageable);

    Page<Product> findAllByCreatedDateAfter(LocalDateTime dateTime, Pageable pageable);

    Page<Product> findAllByDiscountIsNotNull(Pageable pageable);

    Optional<Product> findByHighlight_ProductIdentificationNameAndHighlight_Value(String idName, String highlight);
}
