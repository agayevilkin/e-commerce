package com.example.elcstore.controller;


import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;
import com.example.elcstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void create(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.createProduct(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid ProductRequestDto requestDto) {
        productService.updateProduct(requestDto, id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllProducts")
    @PreAuthorize("permitAll()")
    public CustomPage<ProductPreviewResponseDto> getAllProducts(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return productService.getAllProducts(pageIndex, pageSize);
    }

    @GetMapping("/{category}/all")
    @Operation(summary = "getAllProductsByCategory")
    @PreAuthorize("permitAll()")
    public CustomPage<ProductPreviewResponseDto> getAllProductsByCategory(
            @PathVariable String category,
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return productService.getAllProductsByCategory(category, pageIndex, pageSize);
    }

    @GetMapping("/{category}/{brand}/all")
    @Operation(summary = "getAllProductsByCategoryAndBrand")
    @PreAuthorize("permitAll()")
    public CustomPage<ProductPreviewResponseDto> getAllProductsByCategoryAndBrand(
            @PathVariable String category,
            @PathVariable String brand,
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return productService.getAllProductsByCategoryAndBrand(category, brand, pageIndex, pageSize);
    }

    @GetMapping("/discounted/all")
    @Operation(summary = "getAllDiscountedProducts")
    @PreAuthorize("permitAll()")
    public CustomPage<ProductPreviewResponseDto> getAllDiscountedProducts(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return productService.getAllDiscountedProducts(pageIndex, pageSize);
    }

    @GetMapping("/new-product/all")
    @Operation(summary = "getAllNewProducts")
    @PreAuthorize("permitAll()")
    public CustomPage<ProductPreviewResponseDto> getAllNewProducts(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {
        return productService.getAllNewProducts(pageIndex, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    @PreAuthorize("permitAll()")
    public ProductDetailedResponseDto findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @GetMapping("/{identification_name}/{highlight}")
    @Operation(summary = "findByProductIdentificationNameAndHighlight")
    @PreAuthorize("permitAll()")
    public ProductDetailedResponseDto findByProductIdentificationNameAndHighlight(@PathVariable String highlight, @PathVariable String identification_name) {
        return productService.findByProductIdentificationNameAndHighlight(identification_name, highlight);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
