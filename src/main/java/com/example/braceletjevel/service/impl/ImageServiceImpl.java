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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void deleteImage(Long id) {

    }
    @Override
    public Image createImage(MultipartFile file) throws IOException {
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Image> dbImage = imageRepository.findByName(name);
        return dbImage.map(image -> ImageUtil.decompressImage(image.getImageData())).orElse(null);
    }
}
