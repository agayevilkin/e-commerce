package com.example.braceletjevel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void deleteImage(Long id);

    void createImage(MultipartFile file) throws IOException;

    byte[] getImage(Long id);

}
