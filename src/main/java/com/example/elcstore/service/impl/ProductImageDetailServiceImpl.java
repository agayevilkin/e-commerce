package com.example.elcstore.service.impl;

import com.example.elcstore.domain.ProductImageDetail;
import com.example.elcstore.domain.ProductOption;
import com.example.elcstore.dto.request.ProductImageDetailRequestDto;
import com.example.elcstore.dto.response.ProductImageDetailResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ProductImageDetailRepository;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.ProductImageDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_IMAGE_DETAIL_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_OPTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductImageDetailServiceImpl implements ProductImageDetailService {

    private final ProductImageDetailRepository productImageDetailRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @SneakyThrows
    @Override
    public void createProductImageDetail(ProductImageDetailRequestDto requestDto) {
        ProductOption productOption = productOptionRepository.findById(requestDto.getProductOptionId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        ProductImageDetail productImageDetail = mapper.map(requestDto, ProductImageDetail.class);
        productImageDetail.setImage(imageService.uploadImageWithByteArray(requestDto.getImage().getBytes()));
        productImageDetail.setProductOption(productOption);
        productImageDetailRepository.save(productImageDetail);
    }

    @Override
    public ProductImageDetailResponseDto findById(UUID id) {
        ProductImageDetail productImageDetail = productImageDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_IMAGE_DETAIL_NOT_FOUND.getMessage()));
        return mapper.map(productImageDetail, ProductImageDetailResponseDto.class);
    }

    @SneakyThrows
    @Override
    public void updateProductImageDetail(UUID id, ProductImageDetailRequestDto requestDto) {
        ProductImageDetail productImageDetail = productImageDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_IMAGE_DETAIL_NOT_FOUND.getMessage()));
        ProductOption productOption = productOptionRepository.findById(requestDto.getProductOptionId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        mapper.map(requestDto, productImageDetail);
        productImageDetail.setImage(imageService.updateImageWithByteArray(requestDto.getImage().getBytes(), productImageDetail.getImage().getId()));
        productImageDetail.setProductOption(productOption);
        productImageDetailRepository.save(productImageDetail);
    }

    @Override
    @Transactional
    public void deleteProductImageDetail(UUID id) {
        ProductImageDetail productImageDetail = productImageDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_IMAGE_DETAIL_NOT_FOUND.getMessage()));
        imageService.deleteImage(productImageDetail.getImage().getId());
        productImageDetailRepository.delete(productImageDetail);
    }
}
