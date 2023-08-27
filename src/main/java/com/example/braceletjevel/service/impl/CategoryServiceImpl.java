package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Category;
import com.example.braceletjevel.dto.request.CategoryRequestDto;
import com.example.braceletjevel.dto.response.CategoryResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.CategoryRepository;
import com.example.braceletjevel.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createCategory(CategoryRequestDto requestDto) {
        Category category = mapper.map(requestDto, Category.class);
        repository.save(category);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found!"));
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void updateCategory(Long id, CategoryRequestDto requestDto) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found!"));
        mapper.map(requestDto, category);
        repository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(Long id) {
        return repository.existsById(id);
    }
}
