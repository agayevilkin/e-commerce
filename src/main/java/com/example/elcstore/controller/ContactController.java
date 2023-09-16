package com.example.elcstore.controller;

import com.example.elcstore.dto.request.ContactRequestDto;
import com.example.elcstore.dto.response.ContactResponseDto;
import com.example.elcstore.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody ContactRequestDto requestDto) {
        service.createContact(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ContactResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllContacts")
    public List<ContactResponseDto> getAllContacts() {
        return service.getAllContacts();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ContactRequestDto requestDto) {
        service.updateContact(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteContact(id);
    }
}
