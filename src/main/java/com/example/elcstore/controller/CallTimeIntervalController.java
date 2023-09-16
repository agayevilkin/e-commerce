package com.example.elcstore.controller;

import com.example.elcstore.dto.request.CallTimeIntervalRequestDto;
import com.example.elcstore.dto.response.CallTimeIntervalResponseDto;
import com.example.elcstore.service.CallTimeIntervalService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/call-time-interval")
@RequiredArgsConstructor
public class CallTimeIntervalController {

    private final CallTimeIntervalService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody CallTimeIntervalRequestDto requestDto) {
        service.createCallTimeInterval(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public CallTimeIntervalResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllCallTimeIntervals")
    public List<CallTimeIntervalResponseDto> getAllCallTimeIntervals() {
        return service.getAllCallTimeIntervals();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody CallTimeIntervalRequestDto requestDto) {
        service.updateCallTimeInterval(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteCallTimeInterval(id);
    }
}
