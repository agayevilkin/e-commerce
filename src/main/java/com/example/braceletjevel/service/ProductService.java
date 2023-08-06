package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProduct();

    void deleteProduct(Long id);
}
