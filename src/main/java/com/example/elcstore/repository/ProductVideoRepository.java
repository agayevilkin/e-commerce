package com.example.elcstore.repository;

import com.example.elcstore.domain.ProductVideo;
import com.example.elcstore.domain.enums.VideoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductVideoRepository extends JpaRepository<ProductVideo, UUID> {

    List<ProductVideo> findAllByIsNewTrue();
    List<ProductVideo> findAllByVideoStatus(VideoStatus videoStatus);
}
