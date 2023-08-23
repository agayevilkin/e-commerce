package com.example.braceletjevel.controller;


import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ComputerProductRequestDto;
import com.example.braceletjevel.dto.response.ComputerProductResponseDto;
import com.example.braceletjevel.service.ComputerProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/computer-product")
@RequiredArgsConstructor
public class ComputerProductController {

    private final ComputerProductService computerProductService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public ComputerProductResponseDto createComputerProduct
            (@RequestBody @Valid ComputerProductRequestDto computerProductRequestDto) {
        return computerProductService.createComputerProduct(computerProductRequestDto);
    }

    @PutMapping("/update/{id}")
    public ComputerProductResponseDto createComputerProduct
            (@PathVariable Long id, @RequestBody @Valid ComputerProductRequestDto computerProductRequestDto) {
        return computerProductService.updateComputerProduct(computerProductRequestDto, id);
    }

    @GetMapping("/all")
    public List<ComputerProductResponseDto> getAllComputerProduct() {
        return computerProductService.getAllComputerProduct();
    }

    @GetMapping("/category/all")
    public List<ComputerProductResponseDto> getAllComputerProductByCategory(@RequestParam Categories categories) {
        return computerProductService.getAllComputerProductByCategory(categories);
    }

    @GetMapping("/discounted/all")
    public List<ComputerProductResponseDto> getAllDiscountedComputerProduct() {
        return computerProductService.getAllDiscountedComputerProduct();
    }

    @GetMapping("/new-product/all")
    public List<ComputerProductResponseDto> getAllNewComputerProduct() {
        return computerProductService.getAllNewComputerProduct();
    }

    @GetMapping("/{id}")
    public ComputerProductResponseDto getComputerProduct(@PathVariable Long id) {
        return computerProductService.getComputerProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteComputerProduct(@PathVariable Long id) {
        computerProductService.deleteComputerProduct(id);
    }


}
