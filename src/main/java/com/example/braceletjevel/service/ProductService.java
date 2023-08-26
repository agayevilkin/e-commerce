package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductPreviewResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    ProductPreviewResponseDto createComputerProduct(ProductRequestDto computerProductRequestDto);

    ProductPreviewResponseDto updateComputerProduct(ProductRequestDto computerProductRequestDto, Long id);

    List<ProductPreviewResponseDto> getAllComputerProduct();

    ProductPreviewResponseDto getComputerProductById(Long id);

    void deleteComputerProduct(Long id);

//    List<ProductPreviewResponseDto> getAllComputerProductByCategory(Categories categories);

    List<ProductPreviewResponseDto> getAllNewComputerProduct();

    List<ProductPreviewResponseDto> getAllDiscountedComputerProduct();

    void updateDate(Long id, LocalDateTime dateTime);
}
