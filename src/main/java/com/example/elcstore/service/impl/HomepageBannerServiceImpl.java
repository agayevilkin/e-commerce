package com.example.elcstore.service.impl;

import com.example.elcstore.domain.HomepageBanner;
import com.example.elcstore.dto.request.HomepageBannerCreateRequestDto;
import com.example.elcstore.dto.request.HomepageBannerUpdateRequestDto;
import com.example.elcstore.dto.response.HomepageBannerResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.HomepageBannerRepository;
import com.example.elcstore.service.HomepageBannerService;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.NotFoundException.HOMEPAGE_BANNER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HomepageBannerServiceImpl implements HomepageBannerService {

    private final HomepageBannerRepository homepageBannerRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void createHomepageBanner(HomepageBannerCreateRequestDto requestDto) {
        HomepageBanner homepageBanner = mapper.map(requestDto, HomepageBanner.class);
        homepageBanner.setImageId(imageService.uploadImage(requestDto.getFile()).getId());
        homepageBannerRepository.save(homepageBanner);
    }

    @Override
    public HomepageBannerResponseDto findById(UUID id) {
        return mapper.map(getHomepageBannerById(id), HomepageBannerResponseDto.class);
    }

    @Override
    public List<HomepageBannerResponseDto> getAllHomepageBanners() {
        return homepageBannerRepository.findAll()
                .stream()
                .map((homepageBanner -> mapper.map(homepageBanner, HomepageBannerResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateHomepageBanner(UUID id, @Valid HomepageBannerUpdateRequestDto requestDto) {
        HomepageBanner homepageBanner = getHomepageBannerById(id);
        mapper.map(requestDto, homepageBanner);
        if (requestDto.getFile() != null && !requestDto.getFile().isEmpty()) {
            homepageBanner.setImageId(imageService.updateImage(requestDto.getFile(), homepageBanner.getImageId()).getId());
        }
        homepageBannerRepository.save(homepageBanner);
    }

    @Override
    @Transactional
    public void deleteHomepageBanner(UUID id) {
        HomepageBanner homepageBanner = getHomepageBannerById(id);
        imageService.deleteImage(homepageBanner.getImageId());
        homepageBannerRepository.delete(homepageBanner);
    }

    private HomepageBanner getHomepageBannerById(UUID id) {
        return homepageBannerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HOMEPAGE_BANNER_NOT_FOUND));
    }
}
