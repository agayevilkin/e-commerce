package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
