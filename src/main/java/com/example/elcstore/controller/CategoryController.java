package com.example.elcstore.controller;

import com.example.elcstore.dto.request.CategoryRequestDto;
import com.example.elcstore.dto.response.CategoryResponseDto;
import com.example.elcstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody CategoryRequestDto requestDto) {
        service.createCategory(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public CategoryResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody CategoryRequestDto requestDto) {
        service.updateCategory(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteCategory(id);
    }
}
