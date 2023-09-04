package com.example.elcstore.service;

import com.example.elcstore.dto.request.RatingRequestDto;
import com.example.elcstore.dto.response.RatingResponseDto;

import java.util.UUID;

public interface RatingService {

    void createRating(RatingRequestDto requestDto);

    RatingResponseDto getRating(UUID id);

    void deleteRating(UUID id);

    void update(UUID id, RatingRequestDto requestDto);
}
