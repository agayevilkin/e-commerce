package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.AttributeDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, UUID> {
}
