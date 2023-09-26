package com.example.elcstore.controller;

import com.example.elcstore.domain.enums.StoreLocationStatus;
import com.example.elcstore.dto.request.StoreRequestDto;
import com.example.elcstore.dto.response.StoreResponseDto;
import com.example.elcstore.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
@Validated
public class StoreController {

    private final StoreService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        service.createStore(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public StoreResponseDto findStoreById(@PathVariable UUID id) {
        return service.findStoreById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllStoresByStoreLocationStatus")
    public List<StoreResponseDto> getAllStoresByStoreLocationStatus(@NotNull @RequestParam StoreLocationStatus storeLocationStatus) {
        return service.getAllStoresByStoreLocationStatus(storeLocationStatus);
    }

    @GetMapping("/count")
    @Operation(summary = "getCountOfStoresByLocationStatus")
    public int getCountOfStoresByLocationStatus(@NotNull @RequestParam StoreLocationStatus storeLocationStatus) {
        return service.getCountOfStoresByLocationStatus(storeLocationStatus);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void updateStore(@PathVariable UUID id, @Valid @RequestBody StoreRequestDto requestDto) {
        service.updateStore(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void deleteStore(@PathVariable UUID id) {
        service.deleteStore(id);
    }
}
