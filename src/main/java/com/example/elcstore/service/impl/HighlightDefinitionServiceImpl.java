package com.example.elcstore.service.impl;

import com.example.elcstore.domain.HighlightDefinition;
import com.example.elcstore.dto.request.HighlightDefinitionRequestDto;
import com.example.elcstore.dto.response.HighlightDefinitionResponseDto;
import com.example.elcstore.exception.HighlightDefinitionInUseException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.HighlightDefinitionRepository;
import com.example.elcstore.repository.HighlightRepository;
import com.example.elcstore.service.HighlightDefinitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.HIGHLIGHT_DEFINITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HighlightDefinitionServiceImpl implements HighlightDefinitionService {

    private final HighlightDefinitionRepository highlightDefinitionRepository;
    private final HighlightRepository highlightRepository;
    private final ModelMapper mapper;

    @Override
    public void createHighlightDefinition(HighlightDefinitionRequestDto requestDto) {
        highlightDefinitionRepository.save(mapper.map(requestDto, HighlightDefinition.class));
    }

    @Override
    public HighlightDefinitionResponseDto findById(UUID id) {
        return mapper.map(getHighlightDefinitionById(id), HighlightDefinitionResponseDto.class);
    }

    @Override
    public void updateHighlightDefinition(UUID id, HighlightDefinitionRequestDto requestDto) {
        HighlightDefinition highlightDefinition = getHighlightDefinitionById(id);
        mapper.map(requestDto, highlightDefinition);
        highlightDefinitionRepository.save(highlightDefinition);
    }

    @Override
    public void deleteHighlightDefinition(UUID id) {
        if (checkById(id)) {
            if (existsByHighlightDefinition(id)) {
                throw new HighlightDefinitionInUseException();
            } else highlightDefinitionRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return highlightDefinitionRepository.existsById(id);
    }

    private boolean existsByHighlightDefinition(UUID id) {
        return highlightRepository.existsByHighlightDefinitionId(id);
    }

    private HighlightDefinition getHighlightDefinitionById(UUID id) {
        return highlightDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_DEFINITION_NOT_FOUND.getMessage()));
    }
}
