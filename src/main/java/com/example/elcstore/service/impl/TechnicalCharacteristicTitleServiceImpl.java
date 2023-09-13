package com.example.elcstore.service.impl;

import com.example.elcstore.dto.request.TechnicalCharacteristicTitleRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicTitleResponseDto;
import com.example.elcstore.repository.TechnicalCharacteristicTitleRepository;
import com.example.elcstore.service.TechnicalCharacteristicTitleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechnicalCharacteristicTitleServiceImpl implements TechnicalCharacteristicTitleService {

    private final TechnicalCharacteristicTitleRepository titleRepository;
    private final ModelMapper mapper;

    @Override
    public void createTechnicalCharacteristicTitle(TechnicalCharacteristicTitleRequestDto requestDto) {

    }

    @Override
    public TechnicalCharacteristicTitleResponseDto findById(UUID id) {
        return null;
    }

    @Override
    public void updateTechnicalCharacteristicTitle(UUID id, TechnicalCharacteristicTitleRequestDto requestDto) {

    }

    @Override
    public void deleteTechnicalCharacteristicTitle(UUID id) {

    }
}
