package com.example.elcstore.service;

import com.example.elcstore.dto.request.UserCustomerRequestDto;
import com.example.elcstore.dto.request.UserEmployeeRequestDto;
import com.example.elcstore.dto.response.UserResponseDto;

import java.util.UUID;

public interface UserService {
    void createEmployeeUser(UserEmployeeRequestDto requestDto);

    UserResponseDto findById(UUID id);

    void updateEmployeeUser(UUID id, UserEmployeeRequestDto requestDto);

    void deleteUser(UUID id);

    void createCustomerUser(UserCustomerRequestDto requestDto);

}
