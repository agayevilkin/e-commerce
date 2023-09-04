package com.example.elcstore.repository;

import com.example.elcstore.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
}
