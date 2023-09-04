package com.example.elcstore.repository;

import com.example.elcstore.domain.AttributeDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, UUID> {
}
