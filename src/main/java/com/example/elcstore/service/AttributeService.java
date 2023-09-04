package com.example.elcstore.service;

import com.example.elcstore.dto.request.AttributeRequestDto;
import com.example.elcstore.dto.response.AttributeResponseDto;

import java.util.UUID;

public interface AttributeService {

    void createAttribute(AttributeRequestDto requestDto);

    AttributeResponseDto findById(UUID id);

    void updateAttribute(UUID id, AttributeRequestDto requestDto);

    void deleteAttribute(UUID id);
}
