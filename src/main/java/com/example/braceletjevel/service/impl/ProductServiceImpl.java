package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.Rating;
import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.repository.RatingRepository;
import com.example.braceletjevel.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;

    @Override
    public ProductResponseDto createComputerProduct(ProductRequestDto computerProductRequestDto) {
        Rating rating = ratingRepository.findById(computerProductRequestDto.getRating())
                .orElseThrow(() -> new NotFoundException("Rating not found!"));
        Product product = mapper.map(computerProductRequestDto, Product.class);
        product.setRating(rating);
        product.setCreateDate(LocalDateTime.now());
        productRepository.save(product);
        return mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto updateComputerProduct(ProductRequestDto computerProductRequestDto, Long id) {
        Rating rating = ratingRepository.findById(computerProductRequestDto.getRating())
                .orElseThrow(() -> new NotFoundException("Rating not found!"));
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(computerProductRequestDto, product);
        product.setRating(rating);
        return mapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAllComputerProduct() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getComputerProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        return mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public void deleteComputerProduct(Long id) {
        if (existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<ProductResponseDto> getAllComputerProductByCategory(Categories categories) {
        return productRepository.findAllByCategories(categories)
                .stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllNewComputerProduct() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return productRepository.findAllByCreateDateAfter(yesterday)
                .stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllDiscountedComputerProduct() {
        return productRepository.findAllByDiscountsIsNotEmpty()
                .stream()
                .map(product -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateDate(Long id, LocalDateTime dateTime) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        product.setCreateDate(dateTime);
        productRepository.save(product);
    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
