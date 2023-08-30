package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.dto.request.ProductRequestDto;
import com.example.braceletjevel.dto.response.ProductDetailedResponseDto;
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
    public void createComputerProduct(ProductRequestDto computerProductRequestDto) {
        Product product = mapper.map(computerProductRequestDto, Product.class);
        product.setBrand(null);
        product.setCategory(null);
        product.setColor(null);
        product.setCreatedDate(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRequestDto computerProductRequestDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(computerProductRequestDto, product);
        productRepository.save(product);
    }

    @Override
    public List<ProductPreviewResponseDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailedResponseDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        return mapper.map(product, ProductDetailedResponseDto.class);
    }

    @Override
    public void deleteProduct(Long id) {
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
    public List<ProductPreviewResponseDto> getAllNewProduct() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return productRepository.findAllByCreatedDateAfter(yesterday)
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPreviewResponseDto> getAllDiscountedProduct() {
        return productRepository.findAllByDiscountIsNotNull()
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public void updateDate(Long id, LocalDateTime dateTime) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
//        product.setCreatedDate(dateTime);
//        productRepository.save(product);
//    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
