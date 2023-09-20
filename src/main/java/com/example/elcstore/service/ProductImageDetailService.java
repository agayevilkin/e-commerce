package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductImageDetailRequestDto;
import com.example.elcstore.dto.response.ProductImageDetailResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductImageDetailService {
    void createProductImageDetail(ProductImageDetailRequestDto requestDto);

    ProductImageDetailResponseDto findById(UUID id);

    List<ProductImageDetailResponseDto> findAllByProductOption(UUID id);

    void updateProductImageDetail(UUID id, ProductImageDetailRequestDto requestDto);

    void deleteProductImageDetail(UUID id);

}
