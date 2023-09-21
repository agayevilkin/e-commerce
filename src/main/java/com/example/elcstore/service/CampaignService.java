package com.example.elcstore.service;

import com.example.elcstore.dto.request.CampaignCreateRequestDto;
import com.example.elcstore.dto.request.CampaignUpdateRequestDto;
import com.example.elcstore.dto.response.CampaignDetailedResponseDto;
import com.example.elcstore.dto.response.CampaignPreviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CampaignService {
    void createCampaign(CampaignCreateRequestDto requestDto);

    CampaignDetailedResponseDto findById(UUID id);

    void updateCampaign(UUID id, CampaignUpdateRequestDto requestDto);

    void deleteCampaign(UUID id);

    void updateCampaignImage(UUID id, MultipartFile file);

    List<CampaignPreviewResponseDto> getAllCampaigns();
}
