package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.DiscountRequestDto;
import com.example.braceletjevel.dto.response.DiscountResponseDto;

import java.util.UUID;

public interface DiscountService {

    void create(DiscountRequestDto requestDto);

    DiscountResponseDto getDiscount(UUID id);

    void delete(UUID id);

    void update(UUID id, DiscountRequestDto requestDto);
}
