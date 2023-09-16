package com.example.elcstore.service;

import com.example.elcstore.dto.request.EventRequestDto;
import com.example.elcstore.dto.response.EventResponseDto;

import java.util.List;
import java.util.UUID;

public interface EventService {
    void deleteEvent(UUID id);

    EventResponseDto findById(UUID id);

    void updateEvent(UUID id, EventRequestDto requestDto);

    void createEvent(EventRequestDto requestDto);

    List<EventResponseDto> getAllEvents();
}
