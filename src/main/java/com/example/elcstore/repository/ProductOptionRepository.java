package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ProductOptionRepository extends JpaRepository<ProductOption, UUID>, JpaSpecificationExecutor<ProductOption> {

    List<ProductOption> findAllByProduct_Id(UUID id);
    Page<ProductOption> findAllByProduct_Categories_Id(UUID categoryId, Pageable pageable);
    boolean existsByColorId(UUID id);
}
