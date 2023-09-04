package com.example.elcstore.service;

import com.example.elcstore.dto.response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {
    ImageResponseDto uploadImage(MultipartFile file);

    ImageResponseDto updateImage(MultipartFile file, UUID id);

    void deleteImage(UUID id);

    byte[] getImage(UUID id);

    String createImageUrl(UUID id);

}
