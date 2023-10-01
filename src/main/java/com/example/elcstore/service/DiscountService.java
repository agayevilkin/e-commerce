package com.example.elcstore.service;

import com.example.elcstore.dto.request.DiscountRequestDto;
import com.example.elcstore.dto.response.DiscountResponseDto;

import java.util.List;
import java.util.UUID;

public interface DiscountService {

    void create(DiscountRequestDto requestDto);

    DiscountResponseDto findById(UUID id);

    void delete(UUID id);

    void update(UUID id, DiscountRequestDto requestDto);

    List<DiscountResponseDto> getAllDiscounts();
}
