package com.example.braceletjevel.service;

import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ComputerProductRequestDto;
import com.example.braceletjevel.dto.response.ComputerProductResponseDto;

import java.util.List;

public interface ComputerProductService {
    ComputerProductResponseDto createComputerProduct(ComputerProductRequestDto computerProductRequestDto);

    ComputerProductResponseDto updateComputerProduct(ComputerProductRequestDto computerProductRequestDto, Long id);

    List<ComputerProductResponseDto> getAllComputerProduct();

    ComputerProductResponseDto getComputerProductById(Long id);

    void deleteComputerProduct(Long id);

    List<ComputerProductResponseDto> getAllComputerProductByCategory(Categories categories);

    List<ComputerProductResponseDto> getAllNewComputerProduct();

    List<ComputerProductResponseDto> getAllDiscountedComputerProduct();
}
