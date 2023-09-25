package com.example.elcstore.service;

import com.example.elcstore.domain.enums.VideoStatus;
import com.example.elcstore.dto.request.ProductVideoRequestDto;
import com.example.elcstore.dto.response.ProductVideoResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductVideoService {
    void createProductVideo(ProductVideoRequestDto requestDto);

    ProductVideoResponseDto findById(UUID id);

    void updateProductVideo(UUID id, ProductVideoRequestDto requestDto);

    List<ProductVideoResponseDto> getAllByVideoStatus(VideoStatus videoStatus);

    List<ProductVideoResponseDto> getAllLatestUploaded();

    void deleteProductVideo(UUID id);
}
