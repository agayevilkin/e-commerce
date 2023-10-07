package com.example.elcstore.controller;

import com.example.elcstore.dto.request.CreditRequestDto;
import com.example.elcstore.dto.response.CreditResponseDto;
import com.example.elcstore.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditController {


    private final CreditService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody CreditRequestDto requestDto) {
        service.createCredit(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public CreditResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all/by-product/{product_id}")
    @Operation(summary = "getAllCreditOptionsByProductId")
    public List<CreditResponseDto> getAllCreditOptionsByProductId(@PathVariable UUID product_id) {
        return service.getAllCreditOptionsByProductId(product_id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody CreditRequestDto requestDto) {
        service.updateCredit(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteCredit(id);
    }
}
