package com.example.elcstore.service;

import com.example.elcstore.dto.request.CampaignRequestDto;
import com.example.elcstore.dto.request.CampaignUpdateRequestDto;
import com.example.elcstore.dto.response.CampaignDetailedResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CampaignService {
    void createCampaign(CampaignRequestDto requestDto);

    CampaignDetailedResponseDto findById(UUID id);

    void updateCampaign(UUID id, CampaignUpdateRequestDto requestDto);

    void deleteCampaign(UUID id);

    void updateCampaignImage(UUID id, MultipartFile file);
}
