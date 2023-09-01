package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.CategoryRequestDto;
import com.example.braceletjevel.dto.response.CategoryResponseDto;

import java.util.UUID;

public interface CategoryService {
    void createCategory(CategoryRequestDto requestDto);

    CategoryResponseDto findById(UUID id);

    void updateCategory(UUID id, CategoryRequestDto requestDto);

    void deleteCategory(UUID id);

}
