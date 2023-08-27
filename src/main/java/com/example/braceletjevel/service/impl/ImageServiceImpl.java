package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.domain.util.ImageUtil;
import com.example.braceletjevel.dto.request.ImageRequestDto;
import com.example.braceletjevel.dto.response.ImageResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.repository.ImageRepository;
import com.example.braceletjevel.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;


    @SneakyThrows
    @Override
    @Transactional
    public ImageResponseDto createImage(ImageRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        Image image = new Image();
        image.setImageData(ImageUtil.compressImage(requestDto.getFile().getBytes()));
        image.setProduct(product);
        image.setName(product.getName());
        Image save = imageRepository.save(image);
        save.setImagePath(createImageUrl(image.getId()));
        return mapper.map(save, ImageResponseDto.class);
    }

    @SneakyThrows
    @Override
    public ImageResponseDto updateImage(MultipartFile file, Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found!"));
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        Image save = imageRepository.save(image);
        ImageResponseDto responseDto = mapper.map(save, ImageResponseDto.class);
        responseDto.setImagePath(createImageUrl(image.getId()));
        return responseDto;
    }

    @Transactional
    public byte[] getImage(Long id) {
        Image dbImage = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Image!"));
        return ImageUtil.decompressImage(dbImage.getImageData());
    }

    @Override
    public void deleteImage(Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        }
    }

    private String createImageUrl(Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/image/")
                .path(String.valueOf(id))
                .toUriString();
    }
}
