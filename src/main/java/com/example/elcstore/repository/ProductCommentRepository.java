package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductCommentRepository extends JpaRepository<ProductComment, UUID> {

    List<ProductComment> findAllByProduct_Id(UUID productId);

    int countAllByProduct_Id(UUID productId);
}
