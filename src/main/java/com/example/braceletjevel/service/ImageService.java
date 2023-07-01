package com.example.braceletjevel.service;

import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.dto.response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void deleteImage(Long id);

    Image createImage(MultipartFile file) throws IOException;

    byte[] getImage(String name);
}
