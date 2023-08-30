package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.AddressRequestDto;
import com.example.braceletjevel.dto.response.AddressResponseDto;

public interface AddressService {
    void createAddress(AddressRequestDto requestDto);

    AddressResponseDto findById(Long id);

    void updateAddress(Long id, AddressRequestDto requestDto);

    void deleteAddress(Long id);
}
