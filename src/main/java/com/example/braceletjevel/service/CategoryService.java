package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.CategoryRequestDto;
import com.example.braceletjevel.dto.response.CategoryResponseDto;

public interface CategoryService {
    void createCategory(CategoryRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    void updateCategory(Long id, CategoryRequestDto requestDto);

    void deleteCategory(Long id);

}
