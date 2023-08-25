package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Rating;
import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.RatingRepository;
import com.example.braceletjevel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final ModelMapper mapper;

    @Override
    public RatingResponseDto createRating(RatingRequestDto requestDto) {
        Rating rating = mapper.map(requestDto, Rating.class);
        repository.save(rating);
        return mapper.map(rating, RatingResponseDto.class);
    }

    @Override
    public RatingResponseDto getRating(Long id) {
        Rating rating = repository.findById(id).orElseThrow(() -> new NotFoundException("Rating not found!"));
        return mapper.map(rating, RatingResponseDto.class);
    }

    @Override
    public void deleteRating(Long id) {
        if (existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public RatingResponseDto update(Long id, RatingRequestDto requestDto) {
        Rating rating = repository.findById(id).orElseThrow(() -> new NotFoundException("Rating not found!"));
        mapper.map(requestDto, rating);
        Rating changedRating = repository.save(rating);
        return mapper.map(changedRating, RatingResponseDto.class);
    }

    private boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
