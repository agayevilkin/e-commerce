package com.example.elcstore.controller;

import com.example.elcstore.dto.request.SystemUserRequestDto;
import com.example.elcstore.dto.response.SystemUserResponseDto;
import com.example.elcstore.service.SystemUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/system-user")
@RequiredArgsConstructor
public class SystemUserController {

    private final SystemUserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody SystemUserRequestDto requestDto) {
        service.createSystemUser(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public SystemUserResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody SystemUserRequestDto requestDto) {
        service.updateSystemUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteSystemUser(id);
    }
}
