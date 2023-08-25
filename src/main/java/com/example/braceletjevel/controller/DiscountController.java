package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;
import com.example.braceletjevel.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService service;

    @PostMapping
    public DiscountResponseDto create(@Valid @RequestBody DiscountRequestDto requestDto) {
        return service.create(requestDto);
    }

    @GetMapping("/{id}")
    public DiscountResponseDto getDiscount(@PathVariable Long id) {
        return service.getDiscount(id);
    }

    @PutMapping("/{id}")
    public DiscountResponseDto update(@PathVariable Long id, @Valid @RequestBody DiscountRequestDto requestDto){
        return service.update(id,requestDto);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
