package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.BrandRequestDto;
import com.example.braceletjevel.dto.response.BrandResponseDto;
import com.example.braceletjevel.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create", description = "Post method for create Attribute")
    public void create(@Valid @RequestBody BrandRequestDto requestDto) {
        service.createBrand(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById", description = "Post method for create Attribute")
    public BrandResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update", description = "Post method for create Attribute")
    public void update(@PathVariable Long id, @Valid @RequestBody BrandRequestDto requestDto) {
        service.updateBrand(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete", description = "Post method for create Attribute")
    public void delete(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}
