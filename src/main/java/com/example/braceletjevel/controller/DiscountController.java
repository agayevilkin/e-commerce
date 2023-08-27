package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;
import com.example.braceletjevel.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService service;

    @PostMapping
    @Operation(summary = "create", description = "Post method for create Attribute")
    public DiscountResponseDto create(@Valid @RequestBody DiscountRequestDto requestDto) {
        return service.create(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById", description = "Post method for create Attribute")
    public DiscountResponseDto findById(@PathVariable Long id) {
        return service.getDiscount(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update", description = "Post method for create Attribute")
    public DiscountResponseDto update(@PathVariable Long id, @Valid @RequestBody DiscountRequestDto requestDto) {
        return service.update(id, requestDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete", description = "Post method for create Attribute")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
