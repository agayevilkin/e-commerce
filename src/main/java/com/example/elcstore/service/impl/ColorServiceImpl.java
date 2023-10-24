package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Color;
import com.example.elcstore.dto.request.ColorRequestDto;
import com.example.elcstore.dto.response.ColorResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.exception.ColorInUseException;
import com.example.elcstore.repository.ColorRepository;
import com.example.elcstore.repository.EventRepository;
import com.example.elcstore.repository.ProductOptionRepository;
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
    private final EventRepository eventRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ModelMapper mapper;

    @Override
    public void createColor(ColorRequestDto requestDto) {
        colorRepository.save(mapper.map(requestDto, Color.class));
    }

    @Override
    public ColorResponseDto findById(UUID id) {
        return mapper.map(getColorById(id), ColorResponseDto.class);
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
        Color color = getColorById(id);
        mapper.map(requestDto, color);
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(UUID id) {
        if (checkById(id)) {
            if (existsByColorId(id)) {
                throw new ColorInUseException(id);
            } else colorRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return colorRepository.existsById(id);
    }

    private boolean existsByColorId(UUID id) {
        return eventRepository.existsByColorId(id) || productOptionRepository.existsByColorId(id);
    }

    private Color getColorById(UUID id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));
    }
}
