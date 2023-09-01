package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.enums.StockStatus;
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
    private LocalDateTime createDate;
    private CategoryResponseDto category;
    private ColorResponseDto color;
    private DiscountResponseDto discount;
    private BrandResponseDto brand;
    private List<RatingResponseDto> rating;
    private List<ProductCommentResponseDto> productComment;
    private List<AttributeResponseDto> attribute;
    private List<ImageResponseDto> images;

}