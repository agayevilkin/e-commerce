package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductYouTube;
import com.example.elcstore.domain.enums.VideoTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductYouTubeRepository extends JpaRepository<ProductYouTube, UUID> {

    List<ProductYouTube> findAllByIsNewTrue();
    List<ProductYouTube> findAllByVideoTypeStatus(VideoTypeStatus videoTypeStatus);
}
