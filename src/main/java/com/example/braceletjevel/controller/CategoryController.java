package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.CategoryRequestDto;
import com.example.braceletjevel.dto.response.CategoryResponseDto;
import com.example.braceletjevel.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create", description = "Post method for create Attribute")
    public void create(@Valid @RequestBody CategoryRequestDto requestDto) {
        service.createCategory(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById", description = "Post method for create Attribute")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update", description = "Post method for create Attribute")
    public void update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto requestDto) {
        service.updateCategory(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete", description = "Post method for create Attribute")
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}
