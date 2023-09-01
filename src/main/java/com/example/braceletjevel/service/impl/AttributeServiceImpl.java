package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Attribute;
import com.example.braceletjevel.domain.AttributeDefinition;
import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.AttributeDefinitionRepository;
import com.example.braceletjevel.repository.AttributeRepository;
import com.example.braceletjevel.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository repository;
    private final AttributeDefinitionRepository attributeDefinitionRepository;
    private final ModelMapper mapper;

    @Override
    public void createAttribute(AttributeRequestDto requestDto) {
        AttributeDefinition attributeDefinition = attributeDefinitionRepository.findById(requestDto.getAttributeDefinitionId())
                .orElseThrow(() -> new NotFoundException("Attribute Definition not found!"));
        Attribute attribute = mapper.map(requestDto, Attribute.class);
        attribute.setAttributeDefinition(attributeDefinition);
        repository.save(attribute);
    }

    @Override
    public AttributeResponseDto findById(UUID id) {
        Attribute attribute = repository.findById(id).orElseThrow(() -> new NotFoundException("Attribute not found!"));
        return mapper.map(attribute, AttributeResponseDto.class);
    }

    @Override
    public void updateAttribute(UUID id, AttributeRequestDto requestDto) {
        AttributeDefinition attributeDefinition = attributeDefinitionRepository.findById(requestDto.getAttributeDefinitionId())
                .orElseThrow(() -> new NotFoundException("Attribute Definition not found!"));
        Attribute attribute = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attribute not found!"));
        mapper.map(requestDto, attribute);
        attribute.setAttributeDefinition(attributeDefinition);
        repository.save(attribute);
    }

    @Override
    public void deleteAttribute(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
