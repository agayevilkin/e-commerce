package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;

public interface DiscountService {

    DiscountResponseDto create(DiscountRequestDto requestDto);

    DiscountResponseDto getDiscount(Long id);

    void delete(Long id);
}
