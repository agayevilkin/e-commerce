package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.AttributeRequestDto;
import com.example.braceletjevel.dto.response.AttributeResponseDto;

public interface AttributeService {

    void createAttribute(AttributeRequestDto requestDto);

    AttributeResponseDto findById(Long id);

    void updateAttribute(Long id, AttributeRequestDto requestDto);

    void deleteAttribute(Long id);
}
