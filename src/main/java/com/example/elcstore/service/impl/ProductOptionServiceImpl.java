package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Color;
import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.ProductImageDetail;
import com.example.elcstore.domain.ProductOption;
import com.example.elcstore.domain.enums.StockStatus;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionAdminPreviewResponseDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import com.example.elcstore.dto.request.ProductOptionImageDetailRequestDto;
import com.example.elcstore.exception.ImageUploadException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ColorRepository;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.ProductOptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.ImageUploadException.FAILED_UPLOAD_IMAGE;
import static com.example.elcstore.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final static int MAX_HEIGHT = 315;
    private final static int MAX_WIDTH = 315;

    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    // TODO: 9/20/2023  ProductOptionImageDetailRequestDto list wait test
    public void createProductOption(ProductOptionCreateRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        Color color = colorRepository.findById(requestDto.getColorId())
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND));

        ProductOption productOption = mapper.map(requestDto, ProductOption.class);
        productOption.setThumbnailId(imageService.uploadImageWithByteArray(resizeImage(requestDto.getThumbnail())).getId());
        productOption.setImageDetails(getImageDetailList(requestDto.getImageDetailRequestDtoList(), productOption));
        productOption.setProduct(product);
        productOption.setColor(color);

        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public ProductOptionDetailedResponseDto findById(UUID id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
        return mapper.map(productOption, ProductOptionDetailedResponseDto.class);
    }

    @Override
    public List<ProductOptionAdminPreviewResponseDto> findAllByProductId(UUID id) {
        return productOptionRepository.findAllByProduct_Id(id)
                .stream()
                .map((productOption -> mapper.map(productOption, ProductOptionAdminPreviewResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    // TODO: 9/20/2023  ProductOptionImageDetailRequestDto list wait test
    public void updateProductOption(UUID id, ProductOptionUpdateRequestDto requestDto) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        Color color = colorRepository.findById(requestDto.getColorId())
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND));

        mapper.map(requestDto, productOption);
        productOption.setProduct(product);
        productOption.getImageDetails().addAll(getImageDetailList(requestDto.getImageDetailRequestDtoList(), productOption));
        productOption.setColor(color);

        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public void updateThumbnail(UUID id, MultipartFile file) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
        productOption.setThumbnailId(imageService.updateImageWithByteArray(resizeImage(file), productOption.getThumbnailId()).getId());
        productOptionRepository.save(productOption);
    }

    @Override
    public void updateStockStatus(UUID id, StockStatus stockStatus) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
        productOption.setStockStatus(stockStatus);
        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public void deleteProductOption(UUID id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
        imageService.deleteImage(productOption.getThumbnailId());
        productOptionRepository.delete(productOption);
    }

    // TODO: 9/20/2023 this method is work according to
    //  me but can be not work as not test by Multipart File type
    private List<ProductImageDetail> getImageDetailList(List<ProductOptionImageDetailRequestDto> productImageDetailRequestDto, ProductOption productOption) {
        if (productImageDetailRequestDto != null && !productImageDetailRequestDto.isEmpty()) {
            return productImageDetailRequestDto
                    .stream()
                    .map((imageDetail) -> {
                        ProductImageDetail productImageDetail = mapper.map(imageDetail, ProductImageDetail.class);
                        productImageDetail.setProductOption(productOption);
                        return productImageDetail;
                    })
                    .collect(Collectors.toList());
        } else return Collections.emptyList();
    }

    private byte[] resizeImage(MultipartFile image) {
        try {
            ImageInfoDto info = imageService.getImageWidthAndHeight(image.getBytes());
            if (info.getHeight() > MAX_HEIGHT && info.getWidth() > MAX_WIDTH) {
                return imageService.resizeImage(image.getBytes(), MAX_WIDTH, MAX_HEIGHT);
            } else
                return imageService.resizeImage(image.getBytes(), info.getWidth(), info.getHeight());
        } catch (IOException e) {
            throw new ImageUploadException(FAILED_UPLOAD_IMAGE);
        }
    }
}