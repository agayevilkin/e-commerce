package com.example.elcstore.controller;

import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.response.ProductOptionCategoryBannerResponseDto;
import com.example.elcstore.domain.enums.StockStatus;
import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionAdminPreviewResponseDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import com.example.elcstore.dto.response.ProductOptionRealTimeSearchResponseDto;
import com.example.elcstore.service.ProductOptionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-option")
@RequiredArgsConstructor
@Validated
public class ProductOptionController {

    private final ProductOptionService service;

//    @PostMapping
        @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @ModelAttribute ProductOptionCreateRequestDto requestDto) {
        service.createProductOption(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductOptionDetailedResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all/by-product/{product_id}")
    @Operation(summary = "findAllByProductId")
    public List<ProductOptionAdminPreviewResponseDto> findAllByProductId(@PathVariable UUID product_id) {
        return service.findAllByProductId(product_id);
    }

    @GetMapping("/all/by-category/{category_id}")
    @Operation(summary = "findAllByCategoryId")
    public CustomPage<ProductOptionCategoryBannerResponseDto> findAllByCategoryId(
            @PathVariable UUID category_id,
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return service.findAllByCategoryId(category_id, pageIndex, pageSize);
    }

    @PostMapping("/real-time/search")
    @Operation(summary = "searchProductOptionRealTime")
    public List<ProductOptionRealTimeSearchResponseDto> searchProductOptionRealTime(@RequestParam String query) {
        return service.searchProductOptionRealTime(query);
    }

    @PutMapping("/stock_status/{id}")
    @Operation(summary = "updateStockStatus")
    public void update(@PathVariable UUID id, @NotNull @RequestParam StockStatus stockStatus) {
        service.updateStockStatus(id, stockStatus);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ProductOptionUpdateRequestDto requestDto) {
        service.updateProductOption(id, requestDto);
    }

    @PutMapping(value = "/thumbnail-update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "updateThumbnail")
    public void updateThumbnail(@PathVariable UUID id, @RequestParam("image") MultipartFile file) {
        service.updateThumbnail(id, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteProductOption(id);
    }
}
