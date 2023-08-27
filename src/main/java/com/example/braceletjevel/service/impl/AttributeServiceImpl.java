package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Attribute;
import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.AttributeRepository;
import com.example.braceletjevel.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository repository;
    private final ModelMapper mapper;

    @Override
    public void createAttribute(AttributeRequestDto requestDto) {
        repository.save(mapper.map(requestDto, Attribute.class));
    }

    @Override
    public AttributeResponseDto findById(Long id) {
        Attribute attribute = repository.findById(id).orElseThrow(() -> new NotFoundException("Attribute not found!"));
        return mapper.map(attribute, AttributeResponseDto.class);
    }

    @Override
    public void updateAttribute(Long id, AttributeRequestDto requestDto) {
        Attribute attribute = repository.findById(id).orElseThrow(() -> new NotFoundException("Attribute not found!"));
        mapper.map(requestDto, attribute);
        repository.save(attribute);
    }

    @Override
    public void deleteAttribute(Long id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(Long id) {
        return repository.existsById(id);
    }
}
