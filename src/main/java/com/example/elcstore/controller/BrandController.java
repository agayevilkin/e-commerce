package com.example.elcstore.controller;

import com.example.elcstore.dto.request.BrandRequestDto;
import com.example.elcstore.dto.response.BrandResponseDto;
import com.example.elcstore.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody BrandRequestDto requestDto) {
        service.createBrand(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public BrandResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody BrandRequestDto requestDto) {
        service.updateBrand(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteBrand(id);
    }
}
