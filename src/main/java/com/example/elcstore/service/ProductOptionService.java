package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductOptionService {
    void createProductOption(ProductOptionCreateRequestDto requestDto);

    ProductOptionDetailedResponseDto findById(UUID id);

    void updateProductOption(UUID id, ProductOptionUpdateRequestDto requestDto);

    void deleteProductOption(UUID id);

    void updateThumbnail(UUID id, MultipartFile file);
}
