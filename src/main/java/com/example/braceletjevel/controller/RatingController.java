package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;
import com.example.braceletjevel.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService service;

    @PostMapping
    public RatingResponseDto create(@Valid @RequestBody RatingRequestDto requestDto) {
        return service.createRating(requestDto);
    }

    @GetMapping("/{id}")
    public RatingResponseDto getRating(@PathVariable Long id) {
        return service.getRating(id);
    }

    @PutMapping("/{id}")
    public RatingResponseDto update(@PathVariable Long id, @Valid @RequestBody RatingRequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRating(@PathVariable Long id) {
        service.deleteRating(id);
    }

}
