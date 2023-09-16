package com.example.elcstore.service;

import com.example.elcstore.dto.request.ColorRequestDto;
import com.example.elcstore.dto.response.ColorResponseDto;

import java.util.List;
import java.util.UUID;

public interface ColorService {
    void createColor(ColorRequestDto requestDto);

    ColorResponseDto findById(UUID id);

    void updateColor(UUID id, ColorRequestDto requestDto);

    void deleteColor(UUID id);

    List<ColorResponseDto> getAllColors();
}
