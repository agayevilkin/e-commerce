package com.example.elcstore.service;

import com.example.elcstore.dto.request.TechnicalCharacteristicTitleRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicTitleResponseDto;

import java.util.UUID;

public interface TechnicalCharacteristicTitleService {
    void createTechnicalCharacteristicTitle(TechnicalCharacteristicTitleRequestDto requestDto);

    TechnicalCharacteristicTitleResponseDto findById(UUID id);

    void updateTechnicalCharacteristicTitle(UUID id, TechnicalCharacteristicTitleRequestDto requestDto);

    void deleteTechnicalCharacteristicTitle(UUID id);
}
