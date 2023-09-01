package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.AddressRequestDto;
import com.example.braceletjevel.dto.response.AddressResponseDto;

import java.util.UUID;

public interface AddressService {
    void createAddress(AddressRequestDto requestDto);

    AddressResponseDto findById(UUID id);

    void updateAddress(UUID id, AddressRequestDto requestDto);

    void deleteAddress(UUID id);
}
