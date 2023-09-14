package com.example.elcstore.service;

import com.example.elcstore.domain.Image;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {
    ImageResponseDto uploadImage(MultipartFile file);

    Image uploadImageWithByteArray(byte[] imageData);

    ImageResponseDto updateImage(MultipartFile file, UUID id);

    void deleteImage(UUID id);

    Image updateImageWithByteArray(byte[] imageData, UUID id);

    byte[] getImage(UUID id);

    String createImageUrl(UUID id);

    byte[] resizeImage(byte[] image, int width, int height);

    ImageInfoDto getImageWidthAndHeight(byte[] image);

}
