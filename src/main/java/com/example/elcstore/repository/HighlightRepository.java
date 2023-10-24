package com.example.elcstore.repository;

import com.example.elcstore.domain.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HighlightRepository extends JpaRepository<Highlight, UUID> {

    List<Highlight> findAllByProductIdentificationName(String productIdentificationName);
    boolean existsByHighlightDefinitionId(UUID id);
}
