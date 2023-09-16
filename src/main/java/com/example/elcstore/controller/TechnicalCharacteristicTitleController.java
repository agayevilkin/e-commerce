package com.example.elcstore.controller;

import com.example.elcstore.dto.request.TechnicalCharacteristicTitleRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicTitleResponseDto;
import com.example.elcstore.service.TechnicalCharacteristicTitleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/technical_characteristic_title")
@RequiredArgsConstructor
public class TechnicalCharacteristicTitleController {

    private final TechnicalCharacteristicTitleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody TechnicalCharacteristicTitleRequestDto requestDto) {
        service.createTechnicalCharacteristicTitle(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public TechnicalCharacteristicTitleResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody TechnicalCharacteristicTitleRequestDto requestDto) {
        service.updateTechnicalCharacteristicTitle(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteTechnicalCharacteristicTitle(id);
    }
}
