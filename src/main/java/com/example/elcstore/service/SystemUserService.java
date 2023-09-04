package com.example.elcstore.service;

import com.example.elcstore.dto.request.SystemUserRequestDto;
import com.example.elcstore.dto.response.SystemUserResponseDto;

import java.util.UUID;

public interface SystemUserService {
    void createSystemUser(SystemUserRequestDto requestDto);

    SystemUserResponseDto findById(UUID id);

    void updateSystemUser(UUID id, SystemUserRequestDto requestDto);

    void deleteSystemUser(UUID id);
}
