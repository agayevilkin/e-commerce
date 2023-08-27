package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.enums.StockStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ProductDetailedResponseDto {

    private Long id;
    private String name;
    private String title;
    private String price;
    private String productPic;
    private LocalDateTime createDate;
    private CategoryResponseDto category;
    private ColorResponseDto color;
    private DiscountResponseDto discount;
    private List<RatingResponseDto> rating;
//    private BrandResponseDto brand;
//    private StockStatus stockStatus;
//    private List<ProductCommentResponseDto> productComment;
//    private List<AttributeResponseDto> attribute;
//    private List<ImageResponseDto> images;

}