package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.ProductComment;
import com.example.elcstore.dto.request.ProductCommentRequestDto;
import com.example.elcstore.dto.response.ProductCommentResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ProductCommentRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_COMMENT_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {


    private final ProductCommentRepository repository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void createProductComment(ProductCommentRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        ProductComment productComment = mapper.map(requestDto, ProductComment.class);
        productComment.setProduct(product);
        repository.save(productComment);
    }

    @Override
    public ProductCommentResponseDto getProductComment(UUID id) {
        ProductComment productComment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_COMMENT_NOT_FOUND.getMessage()));
        return mapper.map(productComment, ProductCommentResponseDto.class);
    }

    @Override
    public void updateProductComment(UUID id, ProductCommentRequestDto requestDto) {
        ProductComment productComment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_COMMENT_NOT_FOUND.getMessage()));
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
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
