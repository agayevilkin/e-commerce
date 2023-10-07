package com.example.elcstore.service.impl;

import com.example.elcstore.domain.CardPaymentOptionInfo;
import com.example.elcstore.dto.request.CardOptionInfoCreateDto;
import com.example.elcstore.dto.request.CardOptionInfoUpdateDto;
import com.example.elcstore.dto.response.CardOptionInfoResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CardPaymentOptionInfoRepository;
import com.example.elcstore.service.CardPaymentOptionInfoService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.NotFoundException.CARD_PAYMENT_OPTION_INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CardPaymentOptionInfoServiceImpl implements CardPaymentOptionInfoService {

    private final CardPaymentOptionInfoRepository cardPaymentOptionInfoRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;


    @Override
    @Transactional
    public void createCardPaymentOptionInfo(CardOptionInfoCreateDto requestDto) {
        CardPaymentOptionInfo cardPaymentOptionInfo = mapper.map(requestDto, CardPaymentOptionInfo.class);
        cardPaymentOptionInfo.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        cardPaymentOptionInfoRepository.save(cardPaymentOptionInfo);
    }

    @Override
    public List<CardOptionInfoResponseDto> getAllCardPaymentOptionInfo() {
        return cardPaymentOptionInfoRepository.findAll()
                .stream()
                .map((cardSlider) -> mapper.map(cardSlider, CardOptionInfoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CardOptionInfoResponseDto findById(UUID id) {
        return mapper.map(getCardPaymentOptionInfoById(id), CardOptionInfoResponseDto.class);
    }


    @Override
    @Transactional
    public void updateCardPaymentOptionInfoImage(UUID id, MultipartFile image) {
        CardPaymentOptionInfo cardOptionInfo = getCardPaymentOptionInfoById(id);
        cardOptionInfo.setImageId(imageService.updateImage(image, cardOptionInfo.getImageId()).getId());
        cardPaymentOptionInfoRepository.save(cardOptionInfo);
    }

    @Override
    public void updateCardPaymentOptionInfo(UUID id, CardOptionInfoUpdateDto requestDto) {
        CardPaymentOptionInfo cardOptionInfo = getCardPaymentOptionInfoById(id);
        mapper.map(requestDto, cardOptionInfo);
        cardPaymentOptionInfoRepository.save(cardOptionInfo);
    }

    @Override
    @Transactional
    public void deleteCardPaymentOptionInfo(UUID id) {
        CardPaymentOptionInfo cardOptionInfo = getCardPaymentOptionInfoById(id);
        imageService.deleteImage(cardOptionInfo.getImageId());
        cardPaymentOptionInfoRepository.delete(cardOptionInfo);
    }

    private CardPaymentOptionInfo getCardPaymentOptionInfoById(UUID id) {
        return cardPaymentOptionInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_PAYMENT_OPTION_INFO_NOT_FOUND));
    }

}
