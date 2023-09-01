package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}
