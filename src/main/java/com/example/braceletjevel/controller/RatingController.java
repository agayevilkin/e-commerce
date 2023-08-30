package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;
import com.example.braceletjevel.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService service;

    @PostMapping
    @Operation(summary = "create")
    @ResponseStatus(CREATED)
    public RatingResponseDto create(@Valid @RequestBody RatingRequestDto requestDto) {
        return service.createRating(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public RatingResponseDto findById(@PathVariable Long id) {
        return service.getRating(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public RatingResponseDto update(@PathVariable Long id, @Valid @RequestBody RatingRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteRating(id);
    }

}
