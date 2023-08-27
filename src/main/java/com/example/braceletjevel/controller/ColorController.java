package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.ColorRequestDto;
import com.example.braceletjevel.dto.response.ColorResponseDto;
import com.example.braceletjevel.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody ColorRequestDto requestDto) {
        service.createColor(requestDto);
    }

    @GetMapping("/{id}")
    public ColorResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody ColorRequestDto requestDto) {
        service.updateColor(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteColor(id);
    }
}
