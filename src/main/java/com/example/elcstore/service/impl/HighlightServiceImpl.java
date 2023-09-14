package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Highlight;
import com.example.elcstore.domain.HighlightDefinition;
import com.example.elcstore.dto.request.HighlightRequestDto;
import com.example.elcstore.dto.response.HighlightResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.HighlightDefinitionRepository;
import com.example.elcstore.repository.HighlightRepository;
import com.example.elcstore.service.HighlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.HIGHLIGHT_DEFINITION_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.HIGHLIGHT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HighlightServiceImpl implements HighlightService {

    private final HighlightRepository repository;
    private final HighlightDefinitionRepository highlightDefinitionRepository;
    private final ModelMapper mapper;

    @Override
    public void createHighlight(HighlightRequestDto requestDto) {
        HighlightDefinition highlightDefinition = highlightDefinitionRepository.findById(requestDto.getAttributeDefinitionId())
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_DEFINITION_NOT_FOUND.getMessage()));
        Highlight highlight = mapper.map(requestDto, Highlight.class);
        highlight.setHighlightDefinition(highlightDefinition);
        repository.save(highlight);
    }

    @Override
    public HighlightResponseDto findById(UUID id) {
        Highlight highlight = repository.findById(id).orElseThrow(() -> new NotFoundException(HIGHLIGHT_NOT_FOUND.getMessage()));
        return mapper.map(highlight, HighlightResponseDto.class);
    }

    @Override
    public void updateHighlight(UUID id, HighlightRequestDto requestDto) {
        Highlight highlight = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_NOT_FOUND.getMessage()));
        HighlightDefinition highlightDefinition = highlightDefinitionRepository.findById(requestDto.getAttributeDefinitionId())
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_DEFINITION_NOT_FOUND.getMessage()));
        mapper.map(requestDto, highlight);
        highlight.setHighlightDefinition(highlightDefinition);
        repository.save(highlight);
    }

    @Override
    public void deleteHighlight(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
