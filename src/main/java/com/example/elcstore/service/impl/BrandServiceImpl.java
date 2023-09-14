package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Brand;
import com.example.elcstore.dto.request.BrandRequestDto;
import com.example.elcstore.dto.response.BrandResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.BrandRepository;
import com.example.elcstore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.BRAND_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createBrand(BrandRequestDto requestDto) {
        Brand brand = mapper.map(requestDto, Brand.class);
        repository.save(brand);
    }

    @Override
    public BrandResponseDto findById(UUID id) {
        Brand brand = repository.findById(id).orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        return mapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public void updateBrand(UUID id, BrandRequestDto requestDto) {
        Brand brand = repository.findById(id).orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        mapper.map(requestDto, brand);
        repository.save(brand);
    }

    @Override
    public void deleteBrand(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
