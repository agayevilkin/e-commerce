package com.example.elcstore.service;

import com.example.elcstore.domain.enums.VideoTypeStatus;
import com.example.elcstore.dto.request.ProductYouTubeRequestDto;
import com.example.elcstore.dto.response.ProductYouTubeResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductYouTubeService {
    void createProductYouTube(ProductYouTubeRequestDto requestDto);

    ProductYouTubeResponseDto findById(UUID id);

    void updateProductYouTube(UUID id, ProductYouTubeRequestDto requestDto);

    List<ProductYouTubeResponseDto> getAllByVideoStatus(VideoTypeStatus videoTypeStatus);

    List<ProductYouTubeResponseDto> getAllLatestUploaded();

    void deleteProductYouTube(UUID id);
}
