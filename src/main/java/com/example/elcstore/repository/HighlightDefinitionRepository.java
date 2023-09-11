package com.example.elcstore.repository;

import com.example.elcstore.domain.HighlightDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HighlightDefinitionRepository extends JpaRepository<HighlightDefinition, UUID> {
}
