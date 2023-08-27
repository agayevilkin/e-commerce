package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.BrandRequestDto;
import com.example.braceletjevel.dto.response.BrandResponseDto;
import com.example.braceletjevel.service.BrandService;
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
    public void create(@Valid @RequestBody BrandRequestDto requestDto) {
        service.createBrand(requestDto);
    }

    @GetMapping("/{id}")
    public BrandResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody BrandRequestDto requestDto) {
        service.updateBrand(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}
