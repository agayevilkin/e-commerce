package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.domain.util.ImageUtil;
import com.example.braceletjevel.repository.ImageRepository;
import com.example.braceletjevel.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found image"));
        imageRepository.delete(image);
    }

    @Override
    public void createImage(MultipartFile file) throws IOException {
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
    }

    @Transactional
    public byte[] getImage(Long id) {
        Image dbImage = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found Image!"));
        return ImageUtil.decompressImage(dbImage.getImageData());
    }

}
