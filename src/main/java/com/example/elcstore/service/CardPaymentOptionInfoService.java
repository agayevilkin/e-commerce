package com.example.elcstore.service;

import com.example.elcstore.dto.request.CardOptionInfoCreateDto;
import com.example.elcstore.dto.request.CardOptionInfoUpdateDto;
import com.example.elcstore.dto.response.CardOptionInfoResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CardPaymentOptionInfoService {
    void deleteCardPaymentOptionInfo(UUID id);

    void updateCardPaymentOptionInfoImage(UUID id, MultipartFile file);

    void updateCardPaymentOptionInfo(UUID id, CardOptionInfoUpdateDto requestDto);

    List<CardOptionInfoResponseDto> getAllCardPaymentOptionInfo();

    CardOptionInfoResponseDto findById(UUID id);

    void createCardPaymentOptionInfo(CardOptionInfoCreateDto requestDto);
}
