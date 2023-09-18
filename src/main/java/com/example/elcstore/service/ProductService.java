package com.example.elcstore.service;

import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;

import java.util.UUID;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);

    void updateProduct(ProductRequestDto productRequestDto, UUID id);

    CustomPage<ProductPreviewResponseDto> getAllProducts(Integer pageIndex, Integer pageSize);

    ProductDetailedResponseDto findById(UUID id);

    void deleteProduct(UUID id);

    ProductDetailedResponseDto findByProductIdentificationNameAndHighlight(String idName, String highlight);

    CustomPage<ProductPreviewResponseDto> getAllNewProducts(Integer pageIndex, Integer pageSize);

    CustomPage<ProductPreviewResponseDto> getAllDiscountedProducts(Integer pageIndex, Integer pageSize);

    CustomPage<ProductPreviewResponseDto> getAllProductsByCategory(String category, Integer pageIndex, Integer pageSize);

    CustomPage<ProductPreviewResponseDto> getAllProductsByCategoryAndBrand(String category, String brand, Integer pageIndex, Integer pageSize);
}
