package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Color;
import com.example.elcstore.domain.Event;
import com.example.elcstore.dto.request.EventRequestDto;
import com.example.elcstore.dto.response.EventResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ColorRepository;
import com.example.elcstore.repository.EventRepository;
import com.example.elcstore.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.COLOR_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.EVENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ColorRepository colorRepository;
    private final ModelMapper mapper;

    @Override
    public void createEvent(EventRequestDto requestDto) {
        Event event = mapper.map(requestDto, Event.class);
        event.setColor(getColorById(requestDto.getColorId()));
        eventRepository.save(event);
    }


    @Override
    public void updateEvent(UUID id, EventRequestDto requestDto) {
        Event event = getEventById(id);
        mapper.map(requestDto, event);
        event.setColor(getColorById(requestDto.getColorId()));
        eventRepository.save(event);
    }

    @Override
    public EventResponseDto findById(UUID id) {
        return mapper.map(getEventById(id), EventResponseDto.class);
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map((event -> mapper.map(event, EventResponseDto.class)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteEvent(UUID id) {
        if (existsById(id)) {
            eventRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return eventRepository.existsById(id);
    }

    private Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND.getMessage()));
    }

    private Color getColorById(UUID id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));
    }
}
