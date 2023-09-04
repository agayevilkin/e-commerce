package com.example.elcstore.service.impl;

import com.example.elcstore.domain.AttributeDefinition;
import com.example.elcstore.dto.request.AttributeDefinitionRequestDto;
import com.example.elcstore.dto.response.AttributeDefinitionResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.AttributeDefinitionRepository;
import com.example.elcstore.service.AttributeDefinitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeDefinitionServiceImpl implements AttributeDefinitionService {

    private final AttributeDefinitionRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createAttributeDefinition(AttributeDefinitionRequestDto requestDto) {
        repository.save(mapper.map(requestDto, AttributeDefinition.class));
    }

    @Override
    public AttributeDefinitionResponseDto findById(UUID id) {
        AttributeDefinition attributeDefinition = repository.findById(id).orElseThrow(() -> new NotFoundException(" Attribute Definition not found!"));
        return mapper.map(attributeDefinition, AttributeDefinitionResponseDto.class);
    }

    @Override
    public void updateAttributeDefinition(UUID id, AttributeDefinitionRequestDto requestDto) {
        AttributeDefinition attributeDefinition = repository.findById(id).orElseThrow(() -> new NotFoundException(" Attribute Definition not found!"));
        mapper.map(requestDto, attributeDefinition);
        repository.save(attributeDefinition);
    }

    @Override
    public void deleteAttributeDefinition(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
