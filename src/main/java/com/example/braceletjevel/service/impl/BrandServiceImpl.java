package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Brand;
import com.example.braceletjevel.dto.request.BrandRequestDto;
import com.example.braceletjevel.dto.response.BrandResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.BrandRepository;
import com.example.braceletjevel.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        Brand brand = repository.findById(id).orElseThrow(() -> new NotFoundException("Brand not found!"));
        return mapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public void updateBrand(UUID id, BrandRequestDto requestDto) {
        Brand brand = repository.findById(id).orElseThrow(() -> new NotFoundException("Brand not found!"));
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
