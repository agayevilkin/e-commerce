package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;
import com.example.elcstore.dto.response.ProductCommentUnconfirmedResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductCommentService {
    void createProductComment(ProductCommentRequestDto requestDto);

    ProductCommentResponseDto getProductComment(UUID id);

    List<ProductCommentResponseDto> getAllProductCommentByProductId(UUID id);

    List<ProductCommentUnconfirmedResponseDto> getAllUnconfirmedProductComment();

    void updateProductComment(UUID id, ProductCommentRequestDto requestDto);

    void deleteProductComment(UUID id);

    int getCommentCountByProductId(UUID id);

    double getCommentCountAverageByProductId(UUID id);

}
