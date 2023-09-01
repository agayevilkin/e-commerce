package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.Rating;
import com.example.braceletjevel.dto.request.RatingRequestDto;
import com.example.braceletjevel.dto.response.RatingResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.repository.RatingRepository;
import com.example.braceletjevel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void createRating(RatingRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Product not found!"));
        Rating rating = mapper.map(requestDto, Rating.class);
        rating.setProduct(product);
        repository.save(rating);
    }

    @Override
    public RatingResponseDto getRating(UUID id) {
        Rating rating = repository.findById(id).orElseThrow(() -> new NotFoundException("Rating not found!"));
        return mapper.map(rating, RatingResponseDto.class);
    }

    @Override
    public void deleteRating(UUID id) {
        if (existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public void update(UUID id, RatingRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Product not found!"));
        Rating rating = repository.findById(id).orElseThrow(() -> new NotFoundException("Rating not found!"));
        mapper.map(requestDto, rating);
        rating.setProduct(product);
        repository.save(rating);
    }

    private boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
