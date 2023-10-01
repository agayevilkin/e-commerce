package com.example.elcstore.service;

import com.example.elcstore.dto.request.HomepageWeeklyOfferRequestDto;
import com.example.elcstore.dto.response.HomepageWeeklyOfferResponseDto;

import java.util.List;
import java.util.UUID;

public interface HomepageWeeklyOfferService {
    void createHomepageWeeklyOffer(HomepageWeeklyOfferRequestDto requestDto);

    HomepageWeeklyOfferResponseDto findById(UUID id);

    List<HomepageWeeklyOfferResponseDto> getAllHomepageWeeklyOffers();

    void updateHomepageWeeklyOffer(UUID id, HomepageWeeklyOfferRequestDto requestDto);

    void deleteHomepageWeeklyOffer(UUID id);
}
