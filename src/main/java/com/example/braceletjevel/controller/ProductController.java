package com.example.braceletjevel.controller;


import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductDetailedResponseDto;
import com.example.braceletjevel.dto.response.ProductPreviewResponseDto;
import com.example.braceletjevel.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "create")
    public void create(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.createComputerProduct(requestDto);
    }

    @PatchMapping("/date/{id}")
    @Operation(summary = "updateCreateDate")
    public void updateCreateDate(@PathVariable Long id, @RequestBody LocalDateTime dateTime) {
        productService.updateDate(id, dateTime);
    }

    @PutMapping("{id}")
    @Operation(summary = "update")
    public void update(@PathVariable Long id, @RequestBody @Valid ProductRequestDto requestDto) {
        productService.updateProduct(requestDto, id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllProduct")
    public List<ProductPreviewResponseDto> getAllProduct() {
        return productService.getAllProduct();
    }

//    @GetMapping("/category/all")
//    public List<ProductPreviewResponseDto> getAllComputerProductByCategory(@RequestParam Categories categories) {
//        return productService.getAllComputerProductByCategory(categories);
//    }

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
    public ProductDetailedResponseDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
