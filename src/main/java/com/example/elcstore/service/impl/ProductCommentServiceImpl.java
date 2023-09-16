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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_COMMENT_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {


    private final ProductCommentRepository productCommentRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void createProductComment(ProductCommentRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        ProductComment productComment = mapper.map(requestDto, ProductComment.class);
        productComment.setProduct(product);
        productCommentRepository.save(productComment);
    }

    @Override
    public int getCommentCountByProductId(UUID id) {
        return productCommentRepository.countAllByProduct_Id(id);
    }

    @Override
    public double getCommentCountAverageByProductId(UUID id) {
        int totalCount = 0;
        List<ProductComment> productComments = productCommentRepository.findAllByProduct_Id(id);
        for (ProductComment comment : productComments) {
            totalCount += comment.getStar();
        }
        System.out.println(totalCount);
        return (double) totalCount / productComments.size();
    }

    @Override
    public ProductCommentResponseDto getProductComment(UUID id) {
        ProductComment productComment = productCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_COMMENT_NOT_FOUND.getMessage()));
        return mapper.map(productComment, ProductCommentResponseDto.class);
    }

    @Override
    public List<ProductCommentResponseDto> getAllProductCommentByProductId(UUID id) {
        return productCommentRepository.findAllByProduct_Id(id)
                .stream()
                .map((productComment -> mapper.map(productComment, ProductCommentResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProductComment(UUID id, ProductCommentRequestDto requestDto) {
        ProductComment productComment = productCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_COMMENT_NOT_FOUND.getMessage()));
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        mapper.map(requestDto, productComment);
        productComment.setProduct(product);
        productCommentRepository.save(productComment);
    }

    @Override
    public void deleteProductComment(UUID id) {
        if (existsById(id)) {
            productCommentRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return productCommentRepository.existsById(id);
    }

}
