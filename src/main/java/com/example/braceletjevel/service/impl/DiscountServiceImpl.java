package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Discount;
import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.DiscountRepository;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public void create(DiscountRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        Discount discount = mapper.map(requestDto, Discount.class);
        discount.setProduct(product);
        discount.setDiscountPercentage(calculatePercentage(discount.getCurrentPrice(), product.getPrice()));
        discountRepository.save(discount);
    }

    @Override
    public DiscountResponseDto getDiscount(UUID id) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found discount!"));
        return mapper.map(discount, DiscountResponseDto.class);
    }

    @Override
    public void delete(UUID id) {
        if (existsById(id)) {
            discountRepository.deleteById(id);
        }
    }

    @Override
    public void update(UUID id, DiscountRequestDto requestDto) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new NotFoundException("Discount not found!"));
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        mapper.map(requestDto, discount);
        discount.setProduct(product);
        discount.setDiscountPercentage(calculatePercentage(requestDto.getCurrentPrice(), product.getPrice()));
        discountRepository.save(discount);
    }

    private boolean existsById(UUID id) {
        return discountRepository.existsById(id);
    }

    private int calculatePercentage(double currentPrice, double previousPrice) {
        return (int) (100 - ((currentPrice * 100) / previousPrice));
    }
}
