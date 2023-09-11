package com.example.elcstore.service;

import com.example.elcstore.dto.request.HighlightRequestDto;
import com.example.elcstore.dto.response.HighlightResponseDto;

import java.util.UUID;

public interface HighlightService {

    void createHighlight(HighlightRequestDto requestDto);

    HighlightResponseDto findById(UUID id);

    void updateHighlight(UUID id, HighlightRequestDto requestDto);

    void deleteHighlight(UUID id);
}
