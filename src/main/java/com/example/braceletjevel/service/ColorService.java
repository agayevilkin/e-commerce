package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ColorRequestDto;
import com.example.braceletjevel.dto.response.ColorResponseDto;

public interface ColorService {
    void createColor(ColorRequestDto requestDto);

    ColorResponseDto findById(Long id);

    void updateColor(Long id, ColorRequestDto requestDto);

    void deleteColor(Long id);
}
