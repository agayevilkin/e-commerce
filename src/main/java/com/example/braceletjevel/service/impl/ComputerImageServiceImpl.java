package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.ComputerImage;
import com.example.braceletjevel.domain.ComputerProduct;
import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.domain.util.ImageUtil;
import com.example.braceletjevel.dto.request.ComputerImageRequestDto;
import com.example.braceletjevel.dto.response.ComputerImageResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ComputerImageRepository;
import com.example.braceletjevel.repository.ComputerProductRepository;
import com.example.braceletjevel.repository.ImageRepository;
import com.example.braceletjevel.service.ComputerImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ComputerImageServiceImpl implements ComputerImageService {
    private final ComputerImageRepository repository;
    private final ImageRepository imageRepository;
    private final ComputerProductRepository computerProductRepository;
    private final ModelMapper mapper;

    @Override
    public ComputerImageResponseDto createImage(ComputerImageRequestDto requestDto) {
        ComputerProduct computerProduct = computerProductRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        ComputerImage computerImage = mapper.map(requestDto, ComputerImage.class);
        computerImage.setProduct(computerProduct);
        ComputerImage save = repository.save(computerImage);
        return mapper.map(save, ComputerImageResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public ComputerImageResponseDto createImageAsMultipartFile(MultipartFile file) throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        ComputerImageResponseDto responseDto = mapper.map(image, ComputerImageResponseDto.class);
        responseDto.setImagePath(createImageUrl(image.getId()));
        return responseDto;
    }

    @Override
    public ComputerImageResponseDto update(ComputerImageRequestDto requestDto, Long id) {
        ComputerImage computerImage = repository.findById(id).orElseThrow(() -> new NotFoundException("Image Not Found!"));
        ComputerProduct computerProduct = computerProductRepository.findById(requestDto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(requestDto, computerImage);
        computerImage.setProduct(computerProduct);
        ComputerImage save = repository.save(computerImage);
        return mapper.map(save, ComputerImageResponseDto.class);

    }

    private String createImageUrl(Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/image/")
                .path(String.valueOf(id))
                .toUriString();
    }
}
