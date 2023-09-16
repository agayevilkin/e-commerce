package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ProductImageDetailRequestDto;
import com.example.elcstore.dto.response.ProductImageDetailResponseDto;
import com.example.elcstore.service.ProductImageDetailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-image-detail")
@RequiredArgsConstructor
public class ProductImageDetailController {

    private final ProductImageDetailService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody ProductImageDetailRequestDto requestDto) {
        service.createProductImageDetail(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductImageDetailResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ProductImageDetailRequestDto requestDto) {
        service.updateProductImageDetail(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteProductImageDetail(id);
    }
}
