package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductCommentRequestDto;
import com.example.braceletjevel.dto.response.ProductCommentResponseDto;

import java.util.UUID;

public interface ProductCommentService {
    void createProductComment(ProductCommentRequestDto requestDto);

    ProductCommentResponseDto getProductComment(UUID id);

    void updateProductComment(UUID id, ProductCommentRequestDto requestDto);

    void deleteProductComment(UUID id);
}
