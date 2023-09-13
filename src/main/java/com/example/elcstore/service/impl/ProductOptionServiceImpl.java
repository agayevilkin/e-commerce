package com.example.elcstore.service.impl;

import com.example.elcstore.dto.request.ProductOptionRequestDto;
import com.example.elcstore.dto.response.ProductOptionResponseDto;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ModelMapper mapper;

    @Override
    public void createProductOption(ProductOptionRequestDto requestDto) {

    }

    @Override
    public ProductOptionResponseDto findById(UUID id) {
        return null;
    }

    @Override
    public void updateProductOption(UUID id, ProductOptionRequestDto requestDto) {

    }

    @Override
    public void deleteProductOption(UUID id) {

    }
}
