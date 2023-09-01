package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;
import com.example.braceletjevel.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public void create(@Valid @RequestBody RatingRequestDto requestDto) {
        service.createRating(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public RatingResponseDto findById(@PathVariable UUID id) {
        return service.getRating(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody RatingRequestDto requestDto) {
        service.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.deleteRating(id);
    }

}
