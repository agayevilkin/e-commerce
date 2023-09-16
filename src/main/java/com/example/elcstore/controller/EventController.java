package com.example.elcstore.controller;

import com.example.elcstore.dto.request.EventRequestDto;
import com.example.elcstore.dto.response.EventResponseDto;
import com.example.elcstore.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody EventRequestDto requestDto) {
        service.createEvent(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public EventResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllEvents")
    public List<EventResponseDto> getAllEvents() {
        return service.getAllEvents();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody EventRequestDto requestDto) {
        service.updateEvent(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteEvent(id);
    }
}
