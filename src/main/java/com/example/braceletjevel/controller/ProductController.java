package com.example.braceletjevel.controller;


import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;
import com.example.braceletjevel.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public ProductResponseDto createComputerProduct
            (@RequestBody @Valid ProductRequestDto computerProductRequestDto) {
        return productService.createComputerProduct(computerProductRequestDto);
    }

    @PatchMapping("/update/date/{id}")
    public void updateDate(@PathVariable Long id, @RequestBody LocalDateTime dateTime) {
        productService.updateDate(id, dateTime);
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto createComputerProduct
            (@PathVariable Long id, @RequestBody @Valid ProductRequestDto computerProductRequestDto) {
        return productService.updateComputerProduct(computerProductRequestDto, id);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> getAllComputerProduct() {
        return productService.getAllComputerProduct();
    }

    @GetMapping("/category/all")
    public List<ProductResponseDto> getAllComputerProductByCategory(@RequestParam Categories categories) {
        return productService.getAllComputerProductByCategory(categories);
    }

    @GetMapping("/discounted/all")
    public List<ProductResponseDto> getAllDiscountedComputerProduct() {
        return productService.getAllDiscountedComputerProduct();
    }

    @GetMapping("/new-product/all")
    public List<ProductResponseDto> getAllNewComputerProduct() {
        return productService.getAllNewComputerProduct();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getComputerProduct(@PathVariable Long id) {
        return productService.getComputerProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteComputerProduct(@PathVariable Long id) {
        productService.deleteComputerProduct(id);
    }


}
