package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ImageRequestDto;
import com.example.braceletjevel.dto.response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {
    ImageResponseDto createImage(ImageRequestDto requestDto);

    ImageResponseDto updateImage(MultipartFile file, UUID id);

    void deleteImage(UUID id);

    byte[] getImage(UUID id);
}
