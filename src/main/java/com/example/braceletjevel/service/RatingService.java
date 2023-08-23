package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;

public interface RatingService {

    RatingResponseDto createRating(RatingRequestDto requestDto);

    RatingResponseDto getRating(Long id);

    void deleteRating(Long id);
}
