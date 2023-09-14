package com.example.elcstore.service.impl;

import com.example.elcstore.domain.*;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ColorRepository;
import com.example.elcstore.repository.EventRepository;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.ProductOptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    public void createProductOption(ProductOptionCreateRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        Color color = colorRepository.findById(requestDto.getColorId())
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));

        ProductOption productOption = mapper.map(requestDto, ProductOption.class);
        productOption.setThumbnailId(imageService.uploadImageWithByteArray(getResizedImageByte(requestDto.getImage())).getId());
        productOption.setEvents(getEventList(requestDto.getEvents()));
        productOption.setProduct(product);
        productOption.setColor(color);

        productOptionRepository.save(productOption);
    }


    @Override
    public ProductOptionDetailedResponseDto findById(UUID id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        return mapper.map(productOption, ProductOptionDetailedResponseDto.class);
    }

    @Override
    public void updateProductOption(UUID id, ProductOptionUpdateRequestDto requestDto) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        Color color = colorRepository.findById(requestDto.getColorId())
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND.getMessage()));

        mapper.map(requestDto, product);
        productOption.setEvents(getEventList(requestDto.getEvents()));
        productOption.setProduct(product);
        productOption.setColor(color);

        productOptionRepository.save(productOption);
    }

    @Override
    public void updateThumbnail(UUID id, MultipartFile file) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        productOption.setThumbnailId(imageService.updateImageWithByteArray(getResizedImageByte(file), productOption.getThumbnailId()).getId());
        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public void deleteProductOption(UUID id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage()));
        imageService.deleteImage(productOption.getThumbnailId());
        productOptionRepository.delete(productOption);
    }


    private List<Event> getEventList(List<UUID> events) {
        return events
                .stream()
                .map((e) -> eventRepository.findById(e).orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND.getMessage())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private byte[] getResizedImageByte(MultipartFile image) {
        ImageInfoDto info = imageService.getImageWidthAndHeight(image.getBytes());
        int reducedWidth = info.getWidth() / 2;
        int reducedHeight = info.getHeight() / 2;
        return imageService.resizeImage(image.getBytes(), reducedWidth, reducedHeight);
    }

}
