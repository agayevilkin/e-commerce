package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ProductOptionRequestDto;
import com.example.elcstore.dto.response.ProductOptionResponseDto;
import com.example.elcstore.service.ProductOptionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-option")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody ProductOptionRequestDto requestDto) {
        service.createProductOption(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductOptionResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ProductOptionRequestDto requestDto) {
        service.updateProductOption(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteProductOption(id);
    }
}
