package com.example.braceletjevel.repository;

import com.example.braceletjevel.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> { }
