package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductOptionRepository extends JpaRepository<ProductOption, UUID> {

    List<ProductOption> findAllByProduct_Id(UUID id);
}
