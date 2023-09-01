package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;
import com.example.braceletjevel.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService service;

    @PostMapping
    @Operation(summary = "create")
    @ResponseStatus(CREATED)
    public void create(@Valid @RequestBody DiscountRequestDto requestDto) {
        service.create(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public DiscountResponseDto findById(@PathVariable UUID id) {
        return service.getDiscount(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody DiscountRequestDto requestDto) {
        service.update(id, requestDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
