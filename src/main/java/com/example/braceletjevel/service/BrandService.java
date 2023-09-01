package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.BrandRequestDto;
import com.example.braceletjevel.dto.response.BrandResponseDto;

import java.util.UUID;

public interface BrandService {
    void createBrand(BrandRequestDto requestDto);

    BrandResponseDto findById(UUID id);

    void updateBrand(UUID id, BrandRequestDto requestDto);

    void deleteBrand(UUID id);
}
