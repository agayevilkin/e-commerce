package com.example.elcstore.service.impl;

import com.example.elcstore.dto.request.TechnicalCharacteristicRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicResponseDto;
import com.example.elcstore.repository.TechnicalCharacteristicRepository;
import com.example.elcstore.service.TechnicalCharacteristicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechnicalCharacteristicServiceImpl implements TechnicalCharacteristicService {

    private final TechnicalCharacteristicRepository technicalCharacteristicRepository;
    private final ModelMapper mapper;

    @Override
    public void createTechnicalCharacteristic(TechnicalCharacteristicRequestDto requestDto) {

    }

    @Override
    public TechnicalCharacteristicResponseDto getTechnicalCharacteristic(UUID id) {
        return null;
    }

    @Override
    public void updateTechnicalCharacteristic(UUID id, TechnicalCharacteristicRequestDto requestDto) {

    }

    @Override
    public void deleteTechnicalCharacteristic(UUID id) {

    }
}
