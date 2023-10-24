package com.example.elcstore.repository;

import com.example.elcstore.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    Page<Product> findAllByCategoriesIdAndStatusIsTrue(UUID categoryId, Pageable pageable);
    Page<Product> findAllByBrandIdAndStatusIsTrue(UUID brandId, Pageable pageable);
    Page<Product> findAllByCreatedDateAfterAndStatusIsTrue(LocalDateTime dateTime, Pageable pageable);
    Page<Product> findAllByDiscountIsNotNullAndStatusIsTrue(Pageable pageable);
    Page<Product> findAllByStatusIsTrue(Pageable pageable);

    // TODO: 10/13/2023 simplify this name or change structure
    Optional<Product> findByHighlight_ProductIdentificationNameAndHighlight_ValueAndStatusIsTrue(String idName, String highlight);
    boolean existsByBrandId(UUID id);
    boolean existsByCategoriesId(UUID id);
    boolean existsByEventsId(UUID id);
    boolean existsByTechnicalCharacteristicId(UUID id);
}
