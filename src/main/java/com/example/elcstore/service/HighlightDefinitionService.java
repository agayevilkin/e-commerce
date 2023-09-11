package com.example.elcstore.service;

import com.example.elcstore.dto.request.HighlightDefinitionRequestDto;
import com.example.elcstore.dto.response.HighlightDefinitionResponseDto;

import java.util.UUID;

public interface HighlightDefinitionService {
    void createHighlightDefinition(HighlightDefinitionRequestDto requestDto);

    HighlightDefinitionResponseDto findById(UUID id);

    void updateHighlightDefinition(UUID id, HighlightDefinitionRequestDto requestDto);

    void deleteHighlightDefinition(UUID id);
}
