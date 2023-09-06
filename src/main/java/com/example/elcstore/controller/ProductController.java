package com.example.elcstore.controller;


import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.request.ProductRequestWithCategoryAndBrandDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;
import com.example.elcstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "create")
    public void create(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.createComputerProduct(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @RequestBody @Valid ProductRequestDto requestDto) {
        productService.updateProduct(requestDto, id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllProduct")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<ProductPreviewResponseDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/category/all")
    @Operation(summary = "getAllProductByCategory")
    public List<ProductPreviewResponseDto> getAllProductByCategory(@NotBlank @RequestParam String category) {
        return productService.getAllProductByCategory(category);
    }

    @GetMapping("/category/brand/all")
    @Operation(summary = "getAllProductByCategoryAndBrand")
    public List<ProductPreviewResponseDto> getAllProductByCategoryAndBrand(@Valid ProductRequestWithCategoryAndBrandDto request) {
        return productService.getAllProductByCategoryAndBrand(request);
    }

    @GetMapping("/discounted/all")
    @Operation(summary = "getAllDiscountedProduct")
    public List<ProductPreviewResponseDto> getAllDiscountedProduct() {
        return productService.getAllDiscountedProduct();
    }

    @GetMapping("/new-product/all")
    @Operation(summary = "getAllNewProduct")
    public List<ProductPreviewResponseDto> getAllNewProduct() {
        return productService.getAllNewProduct();
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductDetailedResponseDto findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
