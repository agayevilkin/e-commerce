package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProduct();

    void deleteProduct(Long id);

    void createProduct(ProductRequestDto productRequestDto);
}
