package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Product;
import com.example.braceletjevel.domain.ProductComment;
import com.example.braceletjevel.dto.request.ProductCommentRequestDto;
import com.example.braceletjevel.dto.response.ProductCommentResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.ProductCommentRepository;
import com.example.braceletjevel.repository.ProductRepository;
import com.example.braceletjevel.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {


    private final ProductCommentRepository repository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void createProductComment(ProductCommentRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found!"));
        ProductComment productComment = mapper.map(requestDto, ProductComment.class);
        productComment.setProduct(product);
        repository.save(productComment);
    }

    @Override
    public ProductCommentResponseDto getProductComment(UUID id) {
        ProductComment productComment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Comment not found!"));
        return mapper.map(productComment, ProductCommentResponseDto.class);
    }

    @Override
    public void updateProductComment(UUID id, ProductCommentRequestDto requestDto) {
        ProductComment productComment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Comment not found!"));
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found!"));
        mapper.map(requestDto, productComment);
        productComment.setProduct(product);
        repository.save(productComment);
    }

    @Override
    public void deleteProductComment(UUID id) {
        if (existsById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return repository.existsById(id);
    }

}
