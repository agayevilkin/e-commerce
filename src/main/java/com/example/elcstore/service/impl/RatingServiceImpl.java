package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.Rating;
import com.example.elcstore.dto.request.RatingRequestDto;
import com.example.elcstore.dto.response.RatingResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.repository.RatingRepository;
import com.example.elcstore.service.RatingService;
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
