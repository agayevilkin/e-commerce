package com.example.elcstore.service.impl;

import com.example.elcstore.domain.TechnicalCharacteristicTitle;
import com.example.elcstore.dto.request.TechnicalCharacteristicTitleRequestDto;
import com.example.elcstore.dto.response.TechnicalCharacteristicTitleResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.TechnicalCharacteristicTitleRepository;
import com.example.elcstore.service.TechnicalCharacteristicTitleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TechnicalCharacteristicTitleServiceImpl implements TechnicalCharacteristicTitleService {

    private final TechnicalCharacteristicTitleRepository titleRepository;
    private final ModelMapper mapper;

    @Override
    public void createTechnicalCharacteristicTitle(TechnicalCharacteristicTitleRequestDto requestDto) {
        titleRepository.save(mapper.map(requestDto, TechnicalCharacteristicTitle.class));
    }

    @Override
    public TechnicalCharacteristicTitleResponseDto findById(UUID id) {
        TechnicalCharacteristicTitle title = titleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND.getMessage()));
        return mapper.map(title, TechnicalCharacteristicTitleResponseDto.class);
    }

    @Override
    public void updateTechnicalCharacteristicTitle(UUID id, TechnicalCharacteristicTitleRequestDto requestDto) {
        TechnicalCharacteristicTitle title = titleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND.getMessage()));
        mapper.map(requestDto, title);
        titleRepository.save(title);

    }

    @Override
    public void deleteTechnicalCharacteristicTitle(UUID id) {
        if (existsById(id)) {
            titleRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return titleRepository.existsById(id);
    }

}
