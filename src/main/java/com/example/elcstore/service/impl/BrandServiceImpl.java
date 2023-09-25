package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Brand;
import com.example.elcstore.dto.request.BrandCreateRequestDto;
import com.example.elcstore.dto.request.BrandUpdateRequestDto;
import com.example.elcstore.dto.response.BrandResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.BrandRepository;
import com.example.elcstore.service.BrandService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.BRAND_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void createBrand(BrandCreateRequestDto requestDto) {
        Brand brand = mapper.map(requestDto, Brand.class);
        brand.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        brandRepository.save(brand);
    }

    @Override
    public BrandResponseDto findById(UUID id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        return mapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map((brand -> mapper.map(brand, BrandResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBrand(UUID id, BrandUpdateRequestDto requestDto) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        mapper.map(requestDto, brand);
        if (requestDto.getImage() != null && !requestDto.getImage().isEmpty()) {
            brand.setImageId(imageService.updateImage(requestDto.getImage(), brand.getImageId()).getId());
        }
        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        imageService.deleteImage(brand.getImageId());
        brandRepository.delete(brand);
    }
}
