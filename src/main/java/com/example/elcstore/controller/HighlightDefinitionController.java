package com.example.elcstore.controller;

import com.example.elcstore.dto.request.HighlightDefinitionRequestDto;
import com.example.elcstore.dto.response.HighlightDefinitionResponseDto;
import com.example.elcstore.service.HighlightDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/highlight-definition")
@RequiredArgsConstructor
public class HighlightDefinitionController {

    private final HighlightDefinitionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody HighlightDefinitionRequestDto requestDto) {
        service.createHighlightDefinition(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public HighlightDefinitionResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody HighlightDefinitionRequestDto requestDto) {
        service.updateHighlightDefinition(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteHighlightDefinition(id);
    }
}
