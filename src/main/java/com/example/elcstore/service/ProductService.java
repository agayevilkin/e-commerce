package com.example.elcstore.service;

import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.request.ProductRequestWithCategoryAndBrandDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);
    void updateProduct(ProductRequestDto productRequestDto, UUID id);
    List<ProductPreviewResponseDto> getAllProduct();
    ProductDetailedResponseDto findById(UUID id);
    void deleteProduct(UUID id);
    List<ProductPreviewResponseDto> getAllNewProduct();
    List<ProductPreviewResponseDto> getAllDiscountedProduct();
    List<ProductPreviewResponseDto> getAllProductByCategory(String category);
    List<ProductPreviewResponseDto> getAllProductByCategoryAndBrand(ProductRequestWithCategoryAndBrandDto request);
}
