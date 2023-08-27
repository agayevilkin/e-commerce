package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.BrandRequestDto;
import com.example.braceletjevel.dto.response.BrandResponseDto;

public interface BrandService {
    void createBrand(BrandRequestDto requestDto);

    BrandResponseDto findById(Long id);

    void updateBrand(Long id, BrandRequestDto requestDto);

    void deleteBrand(Long id);
}
