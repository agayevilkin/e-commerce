package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findAllByCategories(Categories categories);

    List<Product> findAllByCreateDateAfter(LocalDateTime dateTime);

    List<Product> findAllByDiscountIsNotNull();
}
