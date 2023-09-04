package com.example.elcstore.repository;

import com.example.elcstore.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColorRepository extends JpaRepository<Color, UUID> {
}
