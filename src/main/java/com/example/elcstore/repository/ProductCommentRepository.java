package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductComment;
import com.example.elcstore.domain.enums.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductCommentRepository extends JpaRepository<ProductComment, UUID> {

    List<ProductComment> findAllByProduct_IdAndCommentStatus(UUID productId, CommentStatus commentStatus);
    List<ProductComment> findAllByCommentStatus(CommentStatus commentStatus);
    int countByProduct_IdAndCommentStatus(UUID productId,CommentStatus commentStatus);
}
