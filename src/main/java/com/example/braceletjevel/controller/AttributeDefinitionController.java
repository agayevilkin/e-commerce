package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.AttributeDefinitionRequestDto;
import com.example.braceletjevel.dto.response.AttributeDefinitionResponseDto;
import com.example.braceletjevel.service.AttributeDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/attribute-definition")
@RequiredArgsConstructor
public class AttributeDefinitionController {

    private final AttributeDefinitionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody AttributeDefinitionRequestDto requestDto) {
        service.createAttributeDefinition(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public AttributeDefinitionResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody AttributeDefinitionRequestDto requestDto) {
        service.updateAttributeDefinition(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteAttributeDefinition(id);
    }
}
