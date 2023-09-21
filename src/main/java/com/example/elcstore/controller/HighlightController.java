package com.example.elcstore.controller;

import com.example.elcstore.dto.request.HighlightRequestDto;
import com.example.elcstore.dto.response.HighlightResponseDto;
import com.example.elcstore.service.HighlightService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/highlight")
@RequiredArgsConstructor
public class HighlightController {

    private final HighlightService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody HighlightRequestDto requestDto) {
        service.createHighlight(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public HighlightResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all/{product_identification_name}")
    @Operation(summary = "getAllByProductIdentificationName")
    public List<HighlightResponseDto> getAllByProductIdentificationName(@PathVariable String product_identification_name) {
        return service.getAllByProductIdentificationName(product_identification_name);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody HighlightRequestDto requestDto) {
        service.updateHighlight(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteHighlight(id);
    }
}
