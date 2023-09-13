package com.example.elcstore.service.impl;

import com.example.elcstore.dto.request.ProductImageDetailRequestDto;
import com.example.elcstore.dto.response.ProductImageDetailResponseDto;
import com.example.elcstore.repository.ProductImageDetailRepository;
import com.example.elcstore.service.ProductImageDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageDetailServiceImpl implements ProductImageDetailService {

    private final ProductImageDetailRepository imageDetailRepository;
    private final ModelMapper mapper;

    @Override
    public void createProductImageDetail(ProductImageDetailRequestDto requestDto) {

    }

    @Override
    public ProductImageDetailResponseDto findById(UUID id) {
        return null;
    }

    @Override
    public void updateProductImageDetail(UUID id, ProductImageDetailRequestDto requestDto) {

    }

    @Override
    public void deleteProductImageDetail(UUID id) {

    }
}
