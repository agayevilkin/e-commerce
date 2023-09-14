package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Campaign;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.request.CampaignRequestDto;
import com.example.elcstore.dto.request.CampaignUpdateRequestDto;
import com.example.elcstore.dto.response.CampaignDetailedResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CampaignRepository;
import com.example.elcstore.service.CampaignService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CAMPAIGN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @SneakyThrows
//    @Transactional
    @Override
    public void createCampaign(CampaignRequestDto requestDto) {
        Campaign campaign = mapper.map(requestDto, Campaign.class);

        // TODO: 9/13/2023 check image uploading for exception state (Transactional)
        MultipartFile originalImage = requestDto.getImage();
        UUID originalImageId = imageService.uploadImage(originalImage).getId();
        UUID thumbnailImageId = imageService.uploadImageWithByteArray(getResizedImageByte(originalImage)).getId();

        campaign.setImageId(originalImageId);
        campaign.setThumbnailImageId(thumbnailImageId);
        campaignRepository.save(campaign);
    }

    @Override
    public CampaignDetailedResponseDto findById(UUID id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException(CAMPAIGN_NOT_FOUND.getMessage()));
        return mapper.map(campaign, CampaignDetailedResponseDto.class);
    }

    @Override
    public void updateCampaign(UUID id, CampaignUpdateRequestDto requestDto) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException(CAMPAIGN_NOT_FOUND.getMessage()));
        mapper.map(requestDto, campaign);
        campaignRepository.save(campaign);
    }

    @Override
    @Transactional
    public void updateCampaignImage(UUID id, MultipartFile file) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException(CAMPAIGN_NOT_FOUND.getMessage()));

        UUID originalImageId = campaign.getImageId();
        UUID thumbnailImageId = campaign.getThumbnailImageId();

        // TODO: 9/13/2023 check image updating for exception state (Transactional)
        UUID updatedOriginalImageId = imageService.updateImage(file, originalImageId).getId();
        UUID updatedThumbnailImageId = imageService.updateImageWithByteArray(getResizedImageByte(file), thumbnailImageId).getId();

        campaign.setImageId(updatedOriginalImageId);
        campaign.setThumbnailImageId(updatedThumbnailImageId);

        campaignRepository.save(campaign);
    }

    @Override
    @Transactional
    public void deleteCampaign(UUID id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException(CAMPAIGN_NOT_FOUND.getMessage()));
        imageService.deleteImage(campaign.getImageId());
        imageService.deleteImage(campaign.getThumbnailImageId());
        campaignRepository.deleteById(id);
    }

    @SneakyThrows
    private byte[] getResizedImageByte(MultipartFile image) {
        ImageInfoDto info = imageService.getImageWidthAndHeight(image.getBytes());
        int reducedWidth = info.getWidth() * 2 / 3;
        int reducedHeight = info.getHeight() * 2 / 3;
        return imageService.resizeImage(image.getBytes(), reducedWidth, reducedHeight);
    }
}
