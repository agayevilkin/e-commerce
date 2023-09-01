package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColorRepository extends JpaRepository<Color, UUID> {
}
