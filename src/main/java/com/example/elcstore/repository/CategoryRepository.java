package com.example.elcstore.repository;

import com.example.elcstore.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findAllByParentId(UUID parentId);

    List<Category> findAllByParentIsNull();
}
