package com.example.elcstore.service;

import com.example.elcstore.dto.request.AddressRequestDto;
import com.example.elcstore.dto.response.AddressResponseDto;

import java.util.UUID;

public interface AddressService {
    void createAddress(AddressRequestDto requestDto);

    AddressResponseDto findById(UUID id);

    void updateAddress(UUID id, AddressRequestDto requestDto);

    void deleteAddress(UUID id);
}
