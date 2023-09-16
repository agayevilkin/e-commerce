package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Color;
import com.example.elcstore.dto.request.ColorRequestDto;
import com.example.elcstore.dto.response.ColorResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ColorRepository;
import com.example.elcstore.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.COLOR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ModelMapper mapper;

    @Override
    public void createColor(ColorRequestDto requestDto) {
        Color color = mapper.map(requestDto, Color.class);
        colorRepository.save(color);
    }

    @Override
    public ColorResponseDto findById(UUID id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));
        return mapper.map(color, ColorResponseDto.class);
    }

    @Override
    public List<ColorResponseDto> getAllColors() {
        return colorRepository.findAll()
                .stream()
                .map((color -> mapper.map(color, ColorResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateColor(UUID id, ColorRequestDto requestDto) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));
        mapper.map(requestDto, color);
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(UUID id) {
        if (checkById(id)) {
            colorRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return colorRepository.existsById(id);
    }
}
