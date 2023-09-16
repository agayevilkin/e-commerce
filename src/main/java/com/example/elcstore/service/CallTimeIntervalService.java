package com.example.elcstore.service;

import com.example.elcstore.dto.request.CallTimeIntervalRequestDto;
import com.example.elcstore.dto.response.CallTimeIntervalResponseDto;

import java.util.List;
import java.util.UUID;

public interface CallTimeIntervalService {
    void createCallTimeInterval(CallTimeIntervalRequestDto requestDto);

    CallTimeIntervalResponseDto findById(UUID id);

    void updateCallTimeInterval(UUID id, CallTimeIntervalRequestDto requestDto);


    void deleteCallTimeInterval(UUID id);

    List<CallTimeIntervalResponseDto> getAllCallTimeIntervals();

}
