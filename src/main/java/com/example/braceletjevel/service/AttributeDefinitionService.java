package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.AttributeDefinitionRequestDto;
import com.example.braceletjevel.dto.response.AttributeDefinitionResponseDto;

import java.util.UUID;

public interface AttributeDefinitionService {
    void createAttributeDefinition(AttributeDefinitionRequestDto requestDto);

    AttributeDefinitionResponseDto findById(UUID id);

    void updateAttributeDefinition(UUID id, AttributeDefinitionRequestDto requestDto);

    void deleteAttributeDefinition(UUID id);
}
