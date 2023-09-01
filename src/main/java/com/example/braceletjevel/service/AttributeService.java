package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;

import java.util.UUID;

public interface AttributeService {

    void createAttribute(AttributeRequestDto requestDto);

    AttributeResponseDto findById(UUID id);

    void updateAttribute(UUID id, AttributeRequestDto requestDto);

    void deleteAttribute(UUID id);
}
