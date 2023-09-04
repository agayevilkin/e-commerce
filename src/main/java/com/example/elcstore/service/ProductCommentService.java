package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;

import java.util.UUID;

public interface ProductCommentService {
    void createProductComment(ProductCommentRequestDto requestDto);

    ProductCommentResponseDto getProductComment(UUID id);

    void updateProductComment(UUID id, ProductCommentRequestDto requestDto);

    void deleteProductComment(UUID id);
}
