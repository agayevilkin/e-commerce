package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
