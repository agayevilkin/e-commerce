package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;
import com.example.elcstore.service.ProductCommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/product-comment")
@RequiredArgsConstructor
public class ProductCommentController {

    private final ProductCommentService service;

    @PostMapping
    @Operation(summary = "create")
    @ResponseStatus(CREATED)
    public void create(@Valid @RequestBody ProductCommentRequestDto requestDto) {
        service.createProductComment(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductCommentResponseDto findById(@PathVariable UUID id) {
        return service.getProductComment(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ProductCommentRequestDto requestDto) {
        service.updateProductComment(id, requestDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteProductComment(id);
    }
}
