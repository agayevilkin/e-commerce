package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.ComputerProduct;
import com.example.braceletjevel.domain.Rating;
import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ComputerProductRequestDto;
import com.example.braceletjevel.dto.response.ComputerProductResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ComputerProductRepository;
import com.example.braceletjevel.repository.RatingRepository;
import com.example.braceletjevel.service.ComputerProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComputerProductServiceImpl implements ComputerProductService {

    private final ComputerProductRepository computerProductRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;

    @Override
    public ComputerProductResponseDto createComputerProduct(ComputerProductRequestDto computerProductRequestDto) {
        Rating rating = ratingRepository.findById(computerProductRequestDto.getRating())
                .orElseThrow(() -> new NotFoundException("Rating not found!"));
        ComputerProduct computerProduct = mapper.map(computerProductRequestDto, ComputerProduct.class);
        computerProduct.setRating(rating);
        computerProduct.setCreateDate(LocalDateTime.now());
        computerProductRepository.save(computerProduct);
        return mapper.map(computerProduct, ComputerProductResponseDto.class);
    }

    @Override
    public ComputerProductResponseDto updateComputerProduct(ComputerProductRequestDto computerProductRequestDto, Long id) {
        Rating rating = ratingRepository.findById(computerProductRequestDto.getRating())
                .orElseThrow(() -> new NotFoundException("Rating not found!"));
        ComputerProduct computerProduct = computerProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(computerProductRequestDto, computerProduct);
        computerProduct.setRating(rating);
        return mapper.map(computerProductRepository.save(computerProduct), ComputerProductResponseDto.class);
    }

    @Override
    public List<ComputerProductResponseDto> getAllComputerProduct() {
        return computerProductRepository.findAll().stream()
                .map(product -> mapper.map(product, ComputerProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ComputerProductResponseDto getComputerProductById(Long id) {
        ComputerProduct computerProduct = computerProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        return mapper.map(computerProduct, ComputerProductResponseDto.class);
    }

    @Override
    public void deleteComputerProduct(Long id) {
        if (existsById(id)) {
            computerProductRepository.deleteById(id);
        }
    }

    @Override
    public List<ComputerProductResponseDto> getAllComputerProductByCategory(Categories categories) {
        return computerProductRepository.findAllByCategories(categories)
                .stream()
                .map(product -> mapper.map(product, ComputerProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ComputerProductResponseDto> getAllNewComputerProduct() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return computerProductRepository.findAllByCreateDateAfter(yesterday)
                .stream()
                .map(product -> mapper.map(product, ComputerProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ComputerProductResponseDto> getAllDiscountedComputerProduct() {
        return computerProductRepository.findAllByDiscountsIsNotEmpty()
                .stream()
                .map(product -> mapper.map(product, ComputerProductResponseDto.class))
                .collect(Collectors.toList());
    }

    private boolean existsById(Long id) {
        return computerProductRepository.existsById(id);
    }
}
