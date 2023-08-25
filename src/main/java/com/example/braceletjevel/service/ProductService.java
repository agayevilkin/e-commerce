package com.example.braceletjevel.service;

import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    ProductResponseDto createComputerProduct(ProductRequestDto computerProductRequestDto);

    ProductResponseDto updateComputerProduct(ProductRequestDto computerProductRequestDto, Long id);

    List<ProductResponseDto> getAllComputerProduct();

    ProductResponseDto getComputerProductById(Long id);

    void deleteComputerProduct(Long id);

    List<ProductResponseDto> getAllComputerProductByCategory(Categories categories);

    List<ProductResponseDto> getAllNewComputerProduct();

    List<ProductResponseDto> getAllDiscountedComputerProduct();

    void updateDate(Long id, LocalDateTime dateTime);
}
