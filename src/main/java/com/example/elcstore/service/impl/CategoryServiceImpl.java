package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Category;
import com.example.elcstore.dto.request.CategoryRequestDto;
import com.example.elcstore.dto.response.CategoryResponseDto;
import com.example.elcstore.exception.CategoryInUseException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CampaignRepository;
import com.example.elcstore.repository.CategoryRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    private final ModelMapper mapper;

    @Override
    public void createCategory(CategoryRequestDto requestDto) {
        Category category = mapper.map(requestDto, Category.class);
        if (requestDto.getParentId() != null) {
            Category parent = getCategoryById(requestDto.getParentId());
            category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDto findById(UUID id) {
        return mapper.map(getCategoryById(id), CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getAllMainCategories() {
        return categoryRepository.findAllByParentIsNull()
                .stream()
                .map((category -> mapper.map(category, CategoryResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponseDto> getAllSubCategoriesByParentId(UUID parentId) {
        return categoryRepository.findAllByParentId(parentId)
                .stream()
                .map((category -> mapper.map(category, CategoryResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(UUID id, CategoryRequestDto requestDto) {
        Category category = getCategoryById(id);
        mapper.map(requestDto, category);
        if (requestDto.getParentId() != null) {
            Category parent = getCategoryById(requestDto.getParentId());
            category.setParent(parent);
        } else category.setParent(null);
        categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(UUID id) {
        if (checkById(id)) {
            if (existsByCategoryId(id)) {
                throw new CategoryInUseException(id);
            } else categoryRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return categoryRepository.existsById(id);
    }

    private Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));
    }

    private boolean existsByCategoryId(UUID id) {
        return productRepository.existsByCategoriesId(id) || campaignRepository.existsByCategoriesId(id);
    }
}
