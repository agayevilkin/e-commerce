package com.example.elcstore.service.impl;

import com.example.elcstore.domain.HighlightDefinition;
import com.example.elcstore.dto.request.HighlightDefinitionRequestDto;
import com.example.elcstore.dto.response.HighlightDefinitionResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.HighlightDefinitionRepository;
import com.example.elcstore.service.HighlightDefinitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HighlightDefinitionServiceImpl implements HighlightDefinitionService {

    private final HighlightDefinitionRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createHighlightDefinition(HighlightDefinitionRequestDto requestDto) {
        repository.save(mapper.map(requestDto, HighlightDefinition.class));
    }

    @Override
    public HighlightDefinitionResponseDto findById(UUID id) {
        HighlightDefinition highlightDefinition = repository.findById(id).orElseThrow(() -> new NotFoundException(" Attribute Definition not found!"));
        return mapper.map(highlightDefinition, HighlightDefinitionResponseDto.class);
    }

    @Override
    public void updateHighlightDefinition(UUID id, HighlightDefinitionRequestDto requestDto) {
        HighlightDefinition highlightDefinition = repository.findById(id).orElseThrow(() -> new NotFoundException(" Attribute Definition not found!"));
        mapper.map(requestDto, highlightDefinition);
        repository.save(highlightDefinition);
    }

    @Override
    public void deleteHighlightDefinition(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
