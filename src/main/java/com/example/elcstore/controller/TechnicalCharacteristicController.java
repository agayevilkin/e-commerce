package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.request.TechnicalCharacteristicRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicResponseDto;
import com.example.elcstore.service.ProductCommentService;
import com.example.elcstore.service.TechnicalCharacteristicService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/technical-characteristic")
@RequiredArgsConstructor
public class TechnicalCharacteristicController {

    private final TechnicalCharacteristicService service;

    @PostMapping
    @Operation(summary = "create")
    @ResponseStatus(CREATED)
    public void create(@Valid @RequestBody TechnicalCharacteristicRequestDto requestDto) {
        service.createTechnicalCharacteristic(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public TechnicalCharacteristicResponseDto findById(@PathVariable UUID id) {
        return service.getTechnicalCharacteristic(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody TechnicalCharacteristicRequestDto requestDto) {
        service.updateTechnicalCharacteristic(id, requestDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteTechnicalCharacteristic(id);
    }
}
