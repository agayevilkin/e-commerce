package com.example.elcstore.service;

import com.example.elcstore.domain.enums.StoreLocationStatus;
import com.example.elcstore.dto.request.StoreRequestDto;
import com.example.elcstore.dto.response.StoreResponseDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface StoreService {
    void createStore(StoreRequestDto requestDto);

    StoreResponseDto findStoreById(UUID id);

    List<StoreResponseDto> getAllStoresByStoreLocationStatus(StoreLocationStatus storeLocationStatus);

    void updateStore(UUID id, StoreRequestDto requestDto);

    void deleteStore(UUID id);

    int getCountOfStoresByLocationStatus(StoreLocationStatus storeLocationStatus);
}
