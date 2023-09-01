package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;

import java.util.UUID;

public interface RatingService {

    void createRating(RatingRequestDto requestDto);

    RatingResponseDto getRating(UUID id);

    void deleteRating(UUID id);

    void update(UUID id, RatingRequestDto requestDto);
}
