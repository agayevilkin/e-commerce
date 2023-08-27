package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;
import com.example.braceletjevel.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/attribute")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "create", description = "Post method for create Attribute")
    public void create(@Valid @RequestBody AttributeRequestDto requestDto) {
        service.createAttribute(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById", description = "Get method for find Attribute")
    public AttributeResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update", description = "Update method for update Attribute")
    public void update(@PathVariable Long id, @Valid @RequestBody AttributeRequestDto requestDto) {
        service.updateAttribute(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete", description = "Delete method for delete Attribute")
    public void delete(@PathVariable Long id) {
        service.deleteAttribute(id);
    }
}
