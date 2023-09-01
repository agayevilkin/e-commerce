package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ColorRequestDto;
import com.example.braceletjevel.dto.response.ColorResponseDto;

import java.util.UUID;

public interface ColorService {
    void createColor(ColorRequestDto requestDto);

    ColorResponseDto findById(UUID id);

    void updateColor(UUID id, ColorRequestDto requestDto);

    void deleteColor(UUID id);
}
