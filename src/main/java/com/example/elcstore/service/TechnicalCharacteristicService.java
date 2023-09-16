package com.example.elcstore.service;

import com.example.elcstore.dto.request.TechnicalCharacteristicRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicResponseDto;

import java.util.UUID;

public interface TechnicalCharacteristicService {
    void createTechnicalCharacteristic(TechnicalCharacteristicRequestDto requestDto);

    TechnicalCharacteristicResponseDto getTechnicalCharacteristic(UUID id);

    void updateTechnicalCharacteristic(UUID id, TechnicalCharacteristicRequestDto requestDto);

    void deleteTechnicalCharacteristic(UUID id);
}
