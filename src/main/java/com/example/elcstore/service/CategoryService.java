package com.example.elcstore.service;

import com.example.elcstore.dto.request.CategoryRequestDto;
import com.example.elcstore.dto.response.CategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    void createCategory(CategoryRequestDto requestDto);

    CategoryResponseDto findById(UUID id);

    void updateCategory(UUID id, CategoryRequestDto requestDto);

    void deleteCategory(UUID id);

    List<CategoryResponseDto> getAllCategories();

}
