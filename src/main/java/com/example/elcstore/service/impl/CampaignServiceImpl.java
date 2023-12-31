package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Campaign;
import com.example.elcstore.domain.Category;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.request.CampaignCreateRequestDto;
import com.example.elcstore.dto.request.CampaignUpdateRequestDto;
import com.example.elcstore.dto.response.CampaignDetailedResponseDto;
import com.example.elcstore.dto.response.CampaignPreviewResponseDto;
import com.example.elcstore.exception.ImageResizeException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CampaignRepository;
import com.example.elcstore.repository.CategoryRepository;
import com.example.elcstore.service.CampaignService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CAMPAIGN_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;


    @Transactional
    @Override
    public void createCampaign(CampaignCreateRequestDto requestDto) {
        Campaign campaign = mapper.map(requestDto, Campaign.class);

        MultipartFile originalImage = requestDto.getImage();
        UUID originalImageId = imageService.uploadImage(originalImage).getId();
        UUID thumbnailImageId = imageService.uploadImageWithByteArray(getResizedImageByte(originalImage)).getId();

        campaign.setCategories(getCategoriesList(requestDto.getCategoryIds()));
        campaign.setImageId(originalImageId);
        campaign.setThumbnailImageId(thumbnailImageId);
        campaignRepository.save(campaign);
    }

    @Override
    @Transactional
    public CampaignDetailedResponseDto findById(UUID id) {
        return mapper.map(getCampaignById(id), CampaignDetailedResponseDto.class);
    }

    @Override
    public void updateCampaign(UUID id, CampaignUpdateRequestDto requestDto) {
        Campaign campaign = getCampaignById(id);
        mapper.map(requestDto, campaign);
        campaign.setCategories(getCategoriesList(requestDto.getCategoryIds()));
        campaignRepository.save(campaign);
    }

    @Override
    @Transactional
    public void updateCampaignImage(UUID id, MultipartFile file) {
        Campaign campaign = getCampaignById(id);

        UUID originalImageId = campaign.getImageId();
        UUID thumbnailImageId = campaign.getThumbnailImageId();

        UUID updatedOriginalImageId = imageService.updateImage(file, originalImageId).getId();
        UUID updatedThumbnailImageId = imageService.updateImageWithByteArray(getResizedImageByte(file), thumbnailImageId).getId();

        campaign.setImageId(updatedOriginalImageId);
        campaign.setThumbnailImageId(updatedThumbnailImageId);

        campaignRepository.save(campaign);
    }

    @Override
    public List<CampaignPreviewResponseDto> getAllCampaigns() {
        return campaignRepository.findAll()
                .stream()
                .map((c) -> mapper.map(c, CampaignPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCampaign(UUID id) {
        Campaign campaign = getCampaignById(id);
        imageService.deleteImage(campaign.getImageId());
        imageService.deleteImage(campaign.getThumbnailImageId());
        campaignRepository.deleteById(id);
    }

    private byte[] getResizedImageByte(MultipartFile image) {
        try {
            ImageInfoDto info = imageService.getImageWidthAndHeight(image.getBytes());
            int reducedWidth = info.getWidth() * 2 / 3;
            int reducedHeight = info.getHeight() * 2 / 3;

            return imageService.resizeImage(image.getBytes(), reducedWidth, reducedHeight);
        } catch (IOException e) {
            throw new ImageResizeException(e.getMessage());
        }
    }

    private Campaign getCampaignById(UUID id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CAMPAIGN_NOT_FOUND.getMessage()));
    }

    private List<Category> getCategoriesList(List<UUID> categoryIds) {
        return categoryIds
                .stream()
                .map((categoryId) -> categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage())))
                .collect(Collectors.toList());
    }
}
