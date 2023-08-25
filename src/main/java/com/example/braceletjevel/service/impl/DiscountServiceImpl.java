package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.Discount;
import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.repository.DiscountRepository;
import com.example.braceletjevel.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    private final ModelMapper mapper;

    @Override
    public DiscountResponseDto create(DiscountRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Not found product!"));
        Discount discount = mapper.map(requestDto, Discount.class);
        discount.setProduct(product);
        return mapper.map(discountRepository.save(discount), DiscountResponseDto.class);
    }

    @Override
    public DiscountResponseDto getDiscount(Long id) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found discount!"));
        return mapper.map(discount, DiscountResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            discountRepository.deleteById(id);
        }
    }

    @Override
    public DiscountResponseDto update(Long id, DiscountRequestDto requestDto) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new NotFoundException("Discount not found!"));
        mapper.map(requestDto, discount);
        discountRepository.save(discount);
        return mapper.map(discount, DiscountResponseDto.class);
    }

    private boolean existsById(Long id) {
        return discountRepository.existsById(id);
    }

}
