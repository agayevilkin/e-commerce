package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.request.ProductRequestWithCategoryAndBrandDto;
import com.example.braceletjevel.dto.response.ProductDetailedResponseDto;
import com.example.braceletjevel.dto.response.ProductPreviewResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void createComputerProduct(ProductRequestDto computerProductRequestDto);

    void updateProduct(ProductRequestDto computerProductRequestDto, UUID id);

    List<ProductPreviewResponseDto> getAllProduct();

    ProductDetailedResponseDto findById(UUID id);

    void deleteProduct(UUID id);

    List<ProductPreviewResponseDto> getAllNewProduct();

    List<ProductPreviewResponseDto> getAllDiscountedProduct();

    List<ProductPreviewResponseDto> getAllProductByCategory(String category);

    List<ProductPreviewResponseDto> getAllProductByCategoryAndBrand(ProductRequestWithCategoryAndBrandDto request);
}
