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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Value("${server.port}")
    private int serverPort;

    @SneakyThrows
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = mapper.map(productRequestDto, Product.class);
        return mapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    @SneakyThrows
    @Override
    public List<ProductResponseDto> getAllProduct() {
        InetAddress localhost = InetAddress.getLocalHost();
        String urlStart = "http://" + localhost.getHostAddress() + ":" + serverPort;
        String endpoint = "/api/image/";
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductResponseDto responseDto = mapper.map(product, ProductResponseDto.class);
                    if (product.getImage() != null) {
                        responseDto.setImagePath(urlStart + endpoint + product.getImage().getName());
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

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
