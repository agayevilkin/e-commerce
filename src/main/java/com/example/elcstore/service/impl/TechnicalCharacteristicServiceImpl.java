package com.example.elcstore.service.impl;

import com.example.elcstore.domain.TechnicalCharacteristic;
import com.example.elcstore.domain.TechnicalCharacteristicTitle;
import com.example.elcstore.dto.request.TechnicalCharacteristicRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.TechnicalCharacteristicRepository;
import com.example.elcstore.repository.TechnicalCharacteristicTitleRepository;
import com.example.elcstore.service.TechnicalCharacteristicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.TECHNICAL_CHARACTERISTIC_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TechnicalCharacteristicServiceImpl implements TechnicalCharacteristicService {

    private final TechnicalCharacteristicRepository technicalCharacteristicRepository;
    private final TechnicalCharacteristicTitleRepository titleRepository;
    private final ModelMapper mapper;

    @Override
    public void createTechnicalCharacteristic(TechnicalCharacteristicRequestDto requestDto) {
        TechnicalCharacteristic characteristic = mapper.map(requestDto, TechnicalCharacteristic.class);
        characteristic.setTitle(getTechnicalCharacteristicTitleById(requestDto.getTechnicalCharacteristicTitleId()));
        technicalCharacteristicRepository.save(characteristic);
    }

    @Override
    public TechnicalCharacteristicResponseDto getTechnicalCharacteristic(UUID id) {
        return mapper.map(getTechnicalCharacteristicById(id), TechnicalCharacteristicResponseDto.class);
    }

    @Override
    public void updateTechnicalCharacteristic(UUID id, TechnicalCharacteristicRequestDto requestDto) {
        TechnicalCharacteristic characteristic = getTechnicalCharacteristicById(id);
        mapper.map(requestDto, characteristic);
        characteristic.setTitle(getTechnicalCharacteristicTitleById(requestDto.getTechnicalCharacteristicTitleId()));
        technicalCharacteristicRepository.save(characteristic);
    }

    @Override
    public void deleteTechnicalCharacteristic(UUID id) {
        if (existsById(id)) {
            technicalCharacteristicRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return technicalCharacteristicRepository.existsById(id);
    }

    private TechnicalCharacteristicTitle getTechnicalCharacteristicTitleById(UUID id) {
        return titleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND.getMessage()));
    }

    private TechnicalCharacteristic getTechnicalCharacteristicById(UUID id) {
        return technicalCharacteristicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TECHNICAL_CHARACTERISTIC_NOT_FOUND.getMessage()));
    }
}
