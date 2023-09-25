package com.example.elcstore.service;

import com.example.elcstore.dto.request.BrandCreateRequestDto;
import com.example.elcstore.dto.request.BrandUpdateRequestDto;
import com.example.elcstore.dto.response.BrandResponseDto;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    void createBrand(BrandCreateRequestDto requestDto);

    BrandResponseDto findById(UUID id);

    void updateBrand(UUID id, BrandUpdateRequestDto requestDto);

    void deleteBrand(UUID id);

    List<BrandResponseDto> getAllBrands();
}
