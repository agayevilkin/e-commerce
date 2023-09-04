package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ColorRequestDto;
import com.example.elcstore.dto.response.ColorResponseDto;
import com.example.elcstore.service.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody ColorRequestDto requestDto) {
        service.createColor(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ColorResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ColorRequestDto requestDto) {
        service.updateColor(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteColor(id);
    }
}
