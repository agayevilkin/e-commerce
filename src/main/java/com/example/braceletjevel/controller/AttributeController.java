package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;
import com.example.braceletjevel.service.AttributeService;
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
    public void create(@Valid @RequestBody AttributeRequestDto requestDto) {
        service.createAttribute(requestDto);
    }

    @GetMapping("/{id}")
    public AttributeResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody AttributeRequestDto requestDto) {
        service.updateAttribute(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteAttribute(id);
    }
}
