package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
}
