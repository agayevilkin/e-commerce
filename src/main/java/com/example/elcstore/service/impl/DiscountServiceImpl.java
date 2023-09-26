package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Discount;
import com.example.elcstore.domain.Product;
import com.example.elcstore.dto.request.DiscountRequestDto;
import com.example.elcstore.dto.response.DiscountResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.DiscountRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.DISCOUNT_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public void create(DiscountRequestDto requestDto) {
        Product product = getProductById(requestDto.getProductId());
        Discount discount = mapper.map(requestDto, Discount.class);
        discount.setProduct(product);
        discount.setDiscountPercentage(calculatePercentage(discount.getCurrentPrice(), product.getPrice()));
        discountRepository.save(discount);
    }

    @Override
    public DiscountResponseDto findById(UUID id) {
        return mapper.map(getDiscountById(id), DiscountResponseDto.class);
    }

    @Override
    public List<DiscountResponseDto> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map((discount -> mapper.map(discount, DiscountResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        if (existsById(id)) {
            discountRepository.deleteById(id);
        }
    }

    @Override
    public void update(UUID id, DiscountRequestDto requestDto) {
        Discount discount = getDiscountById(id);
        Product product = getProductById(requestDto.getProductId());
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

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
    }

    private Discount getDiscountById(UUID id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DISCOUNT_NOT_FOUND.getMessage()));
    }

}
