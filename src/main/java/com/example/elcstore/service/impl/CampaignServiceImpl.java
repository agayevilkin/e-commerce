package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Campaign;
import com.example.elcstore.dto.request.CampaignRequestDto;
import com.example.elcstore.dto.response.CampaignResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CampaignRepository;
import com.example.elcstore.service.CampaignService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @SneakyThrows
    @Override
    @Transactional
    public void createCampaign(CampaignRequestDto requestDto) {
        //TODO complete this logic
        System.out.println(requestDto.getEndingDate() + " " + requestDto.getStartingDate());
        Campaign campaign = mapper.map(requestDto, Campaign.class);
        UUID imageId = imageService.uploadImage(requestDto.getImage()).getId();
        UUID thumbnailImageId = imageService.uploadImage(
                imageService.resizeImage(requestDto.getImage().getBytes(), 600, 300)).getId();
        campaign.setImageId(imageId);
        campaign.setThumbnailImageId(thumbnailImageId);
        campaign.setImagePath(imageService.createImageUrl(imageId));
        campaign.setThumbnailImagePath(imageService.createImageUrl(thumbnailImageId));
        campaignRepository.save(campaign);
    }

    @Override
    public CampaignResponseDto findById(UUID id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException("Campaign not found!"));
        return mapper.map(campaign, CampaignResponseDto.class);
    }

    @Override
    public void updateCampaign(UUID id, CampaignRequestDto requestDto) {
        // TODO: 9/12/2023 complete this
    }

    @Override
    @Transactional
    public void deleteCampaign(UUID id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundException("Campaign not found!"));
        //todo can be change
        imageService.deleteImage(campaign.getImageId());
        imageService.deleteImage(campaign.getThumbnailImageId());
        campaignRepository.deleteById(id);
    }
}
