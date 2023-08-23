package com.example.braceletjevel.controller;


import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;
import com.example.braceletjevel.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @ResponseStatus(CREATED)
    public void create(@ModelAttribute @Valid ProductRequestDto productRequestDto) {
        service.createProduct(productRequestDto);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> getAllProduct() {
        return service.getAllProduct();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}
