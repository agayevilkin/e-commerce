package com.example.elcstore.service;

import com.example.elcstore.dto.request.HomepageBannerCreateRequestDto;
import com.example.elcstore.dto.request.HomepageBannerUpdateRequestDto;
import com.example.elcstore.dto.response.HomepageBannerResponseDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface HomepageBannerService {
    void createHomepageBanner(HomepageBannerCreateRequestDto requestDto);

    HomepageBannerResponseDto findById(UUID id);

    List<HomepageBannerResponseDto> getAllHomepageBanners();

    void updateHomepageBanner(UUID id, @Valid HomepageBannerUpdateRequestDto requestDto);

    void deleteHomepageBanner(UUID id);
}
