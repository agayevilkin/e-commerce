package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCommentRepository extends JpaRepository<ProductComment, UUID> {
}
