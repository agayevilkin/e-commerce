package com.example.elcstore.service;

import com.example.elcstore.dto.request.BrandRequestDto;
import com.example.elcstore.dto.response.BrandResponseDto;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    void createBrand(BrandRequestDto requestDto);

    BrandResponseDto findById(UUID id);

    void updateBrand(UUID id, BrandRequestDto requestDto);

    void deleteBrand(UUID id);

    List<BrandResponseDto> getAllBrands();
}
