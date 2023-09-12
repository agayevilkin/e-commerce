package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Event;
import com.example.elcstore.dto.request.EventRequestDto;
import com.example.elcstore.dto.response.EventResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.EventRepository;
import com.example.elcstore.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper mapper;

    @Override
    public void createEvent(EventRequestDto requestDto) {
        eventRepository.save(mapper.map(requestDto, Event.class));
    }

    @Override
    public void updateEvent(UUID id, EventRequestDto requestDto) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found!"));
        mapper.map(requestDto, event);
        eventRepository.save(event);
    }

    @Override
    public EventResponseDto findById(UUID id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found!"));
        return mapper.map(event, EventResponseDto.class);
    }


    @Override
    public void deleteEvent(UUID id) {
        if(existsById(id)){
            eventRepository.deleteById(id);
        }
    }
    private boolean existsById(UUID id) {
        return eventRepository.existsById(id);
    }

}
