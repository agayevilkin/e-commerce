package com.example.elcstore.service.impl;

import com.example.elcstore.domain.CallTimeInterval;
import com.example.elcstore.dto.request.CallTimeIntervalRequestDto;
import com.example.elcstore.dto.response.CallTimeIntervalResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CallTimeIntervalRepository;
import com.example.elcstore.service.CallTimeIntervalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CALL_TIME_INTERVAL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CallTimeIntervalServiceImpl implements CallTimeIntervalService {

    private final CallTimeIntervalRepository callTimeIntervalRepository;
    private final ModelMapper mapper;

    @Override
    public void createCallTimeInterval(CallTimeIntervalRequestDto requestDto) {
        callTimeIntervalRepository.save(mapper.map(requestDto, CallTimeInterval.class));
    }

    @Override
    public CallTimeIntervalResponseDto findById(UUID id) {
        CallTimeInterval callTimeInterval = callTimeIntervalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CALL_TIME_INTERVAL_NOT_FOUND.getMessage()));
        return mapper.map(callTimeInterval, CallTimeIntervalResponseDto.class);
    }

    @Override
    public void updateCallTimeInterval(UUID id, CallTimeIntervalRequestDto requestDto) {
        CallTimeInterval callTimeInterval = callTimeIntervalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CALL_TIME_INTERVAL_NOT_FOUND.getMessage()));
        mapper.map(requestDto, callTimeInterval);
        callTimeIntervalRepository.save(callTimeInterval);
    }

    @Override
    public void deleteCallTimeInterval(UUID id) {
        if (existsById(id)) {
            callTimeIntervalRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return callTimeIntervalRepository.existsById(id);
    }

}
