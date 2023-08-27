package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductDetailedResponseDto;
import com.example.braceletjevel.dto.response.ProductPreviewResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    void createComputerProduct(ProductRequestDto computerProductRequestDto);

    void updateProduct(ProductRequestDto computerProductRequestDto, Long id);

    List<ProductPreviewResponseDto> getAllProduct();

    ProductDetailedResponseDto findById(Long id);

    void deleteProduct(Long id);

//    List<ProductPreviewResponseDto> getAllComputerProductByCategory(Categories categories);

    List<ProductPreviewResponseDto> getAllNewProduct();

    List<ProductPreviewResponseDto> getAllDiscountedProduct();

    void updateDate(Long id, LocalDateTime dateTime);
}
