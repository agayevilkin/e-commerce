package com.example.elcstore.service;

import com.example.elcstore.dto.request.CampaignRequestDto;
import com.example.elcstore.dto.response.CampaignResponseDto;

import java.util.UUID;

public interface CampaignService {
    void createCampaign(CampaignRequestDto requestDto);

    CampaignResponseDto findById(UUID id);

    void updateCampaign(UUID id, CampaignRequestDto requestDto);

    void deleteCampaign(UUID id);
}
