package com.example.elcstore.service;

import com.example.elcstore.dto.request.AttributeDefinitionRequestDto;
import com.example.elcstore.dto.response.AttributeDefinitionResponseDto;

import java.util.UUID;

public interface AttributeDefinitionService {
    void createAttributeDefinition(AttributeDefinitionRequestDto requestDto);

    AttributeDefinitionResponseDto findById(UUID id);

    void updateAttributeDefinition(UUID id, AttributeDefinitionRequestDto requestDto);

    void deleteAttributeDefinition(UUID id);
}
