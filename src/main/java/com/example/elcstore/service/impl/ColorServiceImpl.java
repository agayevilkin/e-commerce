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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createColor(ColorRequestDto requestDto) {
        Color color = mapper.map(requestDto, Color.class);
        repository.save(color);
    }

    @Override
    public ColorResponseDto findById(UUID id) {
        Color color = repository.findById(id).orElseThrow(() -> new NotFoundException("Color not found!"));
        return mapper.map(color, ColorResponseDto.class);
    }

    @Override
    public void updateColor(UUID id, ColorRequestDto requestDto) {
        Color color = repository.findById(id).orElseThrow(() -> new NotFoundException("Color not found!"));
        mapper.map(requestDto, color);
        repository.save(color);
    }

    @Override
    public void deleteColor(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
