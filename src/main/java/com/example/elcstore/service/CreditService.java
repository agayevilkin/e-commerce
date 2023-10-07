package com.example.elcstore.service;

import com.example.elcstore.dto.request.CreditRequestDto;
import com.example.elcstore.dto.response.CreditResponseDto;

import java.util.List;
import java.util.UUID;

public interface CreditService 
{
    void createCredit(CreditRequestDto requestDto);

    CreditResponseDto findById(UUID id);

    List<CreditResponseDto> getAllCreditOptionsByProductId(UUID productId);

    void updateCredit(UUID id, CreditRequestDto requestDto);

    void deleteCredit(UUID id);
}
