package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Category;
import com.example.elcstore.dto.request.CategoryRequestDto;
import com.example.elcstore.dto.response.CategoryResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CategoryRepository;
import com.example.elcstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CATEGORY_NOT_FOUND;

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
    public CategoryResponseDto findById(UUID id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void updateCategory(UUID id, CategoryRequestDto requestDto) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));
        mapper.map(requestDto, category);
        repository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
