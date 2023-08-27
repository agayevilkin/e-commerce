package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.Rating;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductPreviewResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.repository.RatingRepository;
import com.example.braceletjevel.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;

    @Override
    public ProductPreviewResponseDto createComputerProduct(ProductRequestDto computerProductRequestDto) {
        Product product = mapper.map(computerProductRequestDto, Product.class);
        product.setBrand(null);
        product.setCategory(null);
        product.setColor(null);
        product.setCreateDate(LocalDateTime.now());
        productRepository.save(product);
        return mapper.map(product, ProductPreviewResponseDto.class);
    }

    @Override
    public ProductPreviewResponseDto updateComputerProduct(ProductRequestDto computerProductRequestDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(computerProductRequestDto, product);
        return mapper.map(productRepository.save(product), ProductPreviewResponseDto.class);
    }

    @Override
    public List<ProductPreviewResponseDto> getAllComputerProduct() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductPreviewResponseDto getComputerProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        return mapper.map(product, ProductPreviewResponseDto.class);
    }

    @Override
    public void deleteComputerProduct(Long id) {
        if (existsById(id)) {
            productRepository.deleteById(id);
        }
    }

//    @Override
//    public List<ProductPreviewResponseDto> getAllComputerProductByCategory(Categories categories) {
//        return productRepository.findAllByCategories(categories)
//                .stream()
//                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProductPreviewResponseDto> getAllNewComputerProduct() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return productRepository.findAllByCreateDateAfter(yesterday)
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPreviewResponseDto> getAllDiscountedComputerProduct() {
        return productRepository.findAllByDiscountIsNotNull()
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateDate(Long id, LocalDateTime dateTime) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        product.setCreateDate(dateTime);
        productRepository.save(product);
    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
