package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.StockStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductDetailedResponseDto {

    private UUID id;
    private double price;
    private String name;
    private String title;
    private StockStatus stockStatus;
    private String productPic;
    private LocalDateTime createdDate;
    private CategoryResponseDto category;
    private ColorResponseDto color;
    private DiscountResponseDto discount;
    private BrandResponseDto brand;
    private List<ProductCommentResponseDto> productComment;
    private List<HighlightResponseDto> attribute;
    private List<ImageResponseDto> images;

}