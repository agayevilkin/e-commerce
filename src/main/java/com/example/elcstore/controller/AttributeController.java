package com.example.elcstore.controller;

import com.example.elcstore.dto.request.AttributeRequestDto;
import com.example.elcstore.dto.response.AttributeResponseDto;
import com.example.elcstore.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/attribute")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody AttributeRequestDto requestDto) {
        service.createAttribute(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public AttributeResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody AttributeRequestDto requestDto) {
        service.updateAttribute(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteAttribute(id);
    }
}
