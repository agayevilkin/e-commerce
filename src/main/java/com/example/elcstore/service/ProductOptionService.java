package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductOptionRequestDto;
import com.example.elcstore.dto.response.ProductOptionResponseDto;

import java.util.UUID;

public interface ProductOptionService {
    void createProductOption(ProductOptionRequestDto requestDto);

    ProductOptionResponseDto findById(UUID id);

    void updateProductOption(UUID id, ProductOptionRequestDto requestDto);

    void deleteProductOption(UUID id);
}
