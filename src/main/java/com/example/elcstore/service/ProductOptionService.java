package com.example.elcstore.service;

import com.example.elcstore.domain.enums.StockStatus;
import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionAdminPreviewResponseDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductOptionService {
    void createProductOption(ProductOptionCreateRequestDto requestDto);

    ProductOptionDetailedResponseDto findById(UUID id);

    List<ProductOptionAdminPreviewResponseDto> findAllByProductId(UUID id);

    void updateProductOption(UUID id, ProductOptionUpdateRequestDto requestDto);

    void deleteProductOption(UUID id);

    void updateThumbnail(UUID id, MultipartFile file);

    void updateStockStatus(UUID id, StockStatus stockStatus);

}
