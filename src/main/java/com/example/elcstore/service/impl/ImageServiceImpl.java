package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Image;
import com.example.elcstore.domain.util.ImageUtil;
import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ImageRepository;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    @SneakyThrows
    @Override
    public ImageResponseDto uploadImage(MultipartFile file) {
        Image image = new Image();
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        Image save = imageRepository.save(image);
        return mapper.map(save, ImageResponseDto.class);
    }

    @SneakyThrows
    @Override
    public ImageResponseDto updateImage(MultipartFile file, UUID id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found!"));
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        Image save = imageRepository.save(image);
        ImageResponseDto responseDto = mapper.map(save, ImageResponseDto.class);
        responseDto.setImagePath(createImageUrl(image.getId()));
        return responseDto;
    }

    @Transactional
    public byte[] getImage(UUID id) {
        Image dbImage = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Image!"));
        return ImageUtil.decompressImage(dbImage.getImageData());
    }

    @Override
    public void deleteImage(UUID id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        }
    }

    @Override
    public String createImageUrl(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/image/")
                .path(String.valueOf(id))
                .toUriString();
    }
}
