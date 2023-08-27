package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ImageRequestDto;
import com.example.braceletjevel.dto.response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageResponseDto createImage(ImageRequestDto requestDto);

    ImageResponseDto updateImage(MultipartFile file, Long id);

    void deleteImage(Long id);

    byte[] getImage(Long id);
}
