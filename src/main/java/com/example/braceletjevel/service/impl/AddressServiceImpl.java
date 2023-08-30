package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.dto.request.AddressRequestDto;
import com.example.braceletjevel.dto.response.AddressResponseDto;
import com.example.braceletjevel.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Override
    public void createAddress(AddressRequestDto requestDto) {

    }

    @Override
    public AddressResponseDto findById(Long id) {
        return null;
    }

    @Override
    public void updateAddress(Long id, AddressRequestDto requestDto) {

    }

    @Override
    public void deleteAddress(Long id) {

    }
}
