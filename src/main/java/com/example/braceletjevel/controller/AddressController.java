package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.AddressRequestDto;
import com.example.braceletjevel.dto.response.AddressResponseDto;
import com.example.braceletjevel.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody AddressRequestDto requestDto) {
        service.createAddress(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public AddressResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable Long id, @Valid @RequestBody AddressRequestDto requestDto) {
        service.updateAddress(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable Long id) {
        service.deleteAddress(id);
    }
}
