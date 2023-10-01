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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.HIGHLIGHT_DEFINITION_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.HIGHLIGHT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HighlightServiceImpl implements HighlightService {

    private final HighlightRepository highlightRepository;
    private final HighlightDefinitionRepository highlightDefinitionRepository;
    private final ModelMapper mapper;

    @Override
    public void createHighlight(HighlightRequestDto requestDto) {
        HighlightDefinition highlightDefinition = getHighlightDefinitionById(requestDto.getHighlightDefinitionId());
        Highlight highlight = mapper.map(requestDto, Highlight.class);
        highlight.setHighlightDefinition(highlightDefinition);
        highlightRepository.save(highlight);
    }

    @Override
    public HighlightResponseDto findById(UUID id) {
        return mapper.map(getHighlightById(id), HighlightResponseDto.class);
    }

    @Override
    public List<HighlightResponseDto> getAllByProductIdentificationName(String productIdentificationName) {
        return highlightRepository.findAllByProductIdentificationName(productIdentificationName)
                .stream()
                .map((idN) -> mapper.map(idN, HighlightResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateHighlight(UUID id, HighlightRequestDto requestDto) {
        Highlight highlight = getHighlightById(id);
        HighlightDefinition highlightDefinition = getHighlightDefinitionById(requestDto.getHighlightDefinitionId());
        mapper.map(requestDto, highlight);
        highlight.setHighlightDefinition(highlightDefinition);
        highlightRepository.save(highlight);
    }

    @Override
    public void deleteHighlight(UUID id) {
        if (checkById(id)) {
            highlightRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return highlightRepository.existsById(id);
    }

    private HighlightDefinition getHighlightDefinitionById(UUID id) {
        return highlightDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_DEFINITION_NOT_FOUND.getMessage()));
    }

    private Highlight getHighlightById(UUID id) {
        return highlightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_NOT_FOUND.getMessage()));
    }
}
