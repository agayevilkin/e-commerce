package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.ComputerProduct;
import com.example.braceletjevel.domain.enums.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComputerProductRepository extends JpaRepository<ComputerProduct, Long> {

    List<ComputerProduct> findAllByCategories(Categories categories);

    List<ComputerProduct> findAllByCreateDateAfter(LocalDateTime dateTime);

    List<ComputerProduct> findAllByDiscountsIsNotEmpty();
}
