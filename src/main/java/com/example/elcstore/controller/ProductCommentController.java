package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;
import com.example.elcstore.dto.response.ProductCommentUnconfirmedResponseDto;
import com.example.elcstore.service.ProductCommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/product-comment")
@RequiredArgsConstructor
public class ProductCommentController {

    private final ProductCommentService service;

    // TODO: 9/17/2023 check all method name
    // TODO: 9/17/2023 check Request and Response Dto fields
    // TODO: 9/17/2023 check fetch types
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

    @GetMapping("/all/{id}")
    @Operation(summary = "getAllProductCommentByProductId")
    public List<ProductCommentResponseDto> getAllProductCommentByProductId(@PathVariable UUID id) {
        return service.getAllProductCommentByProductId(id);
    }

    @GetMapping("/unconfirmed/all")
    @Operation(summary = "getAllUnconfirmedProductComment")
    public List<ProductCommentUnconfirmedResponseDto> getAllUnconfirmedProductComment() {
        return service.getAllUnconfirmedProductComment();
    }

    @GetMapping("/count/{id}")
    @Operation(summary = "getCommentCountByProductId")
    public int getCommentCountByProductId(@PathVariable UUID id) {
        return service.getCommentCountByProductId(id);
    }

    @GetMapping("/count-average/{id}")
    @Operation(summary = "getCommentCountAverageByProductId")
    public double getCommentCountAverageByProductId(@PathVariable UUID id) {
        return service.getCommentCountAverageByProductId(id);
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
