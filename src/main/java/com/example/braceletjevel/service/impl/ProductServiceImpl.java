package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductResponseDto;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.service.ImageService;
import com.example.braceletjevel.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final ImageService imageService;

    @SneakyThrows
    @Override
    public List<ProductResponseDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductResponseDto responseDto = mapper.map(product, ProductResponseDto.class);
                    if (product.getImage() != null) {
                        responseDto.setImagePath(createImageUrl(product.getImage().getId()));
                    }
                    return responseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        if (existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    @SneakyThrows
    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Image image = imageService.createImage(productRequestDto.getFile());
        Product product = mapper.map(productRequestDto, Product.class);
        product.setImage(image);
        productRepository.save(product);
    }

    private String createImageUrl(Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/image/")
                .path(String.valueOf(id))
                .toUriString();
    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
