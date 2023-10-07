package com.example.elcstore.repository;

import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.ProductOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> , JpaSpecificationExecutor<Product> {

    Page<Product> findAllByCategoriesId(UUID categoryId, Pageable pageable);

    Page<Product> findAllByBrandId(UUID brandId, Pageable pageable);

    Page<Product> findAllByCreatedDateAfter(LocalDateTime dateTime, Pageable pageable);

    Page<Product> findAllByDiscountIsNotNull(Pageable pageable);

    Optional<Product> findByHighlight_ProductIdentificationNameAndHighlight_Value(String idName, String highlight);
}
