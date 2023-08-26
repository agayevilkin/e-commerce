package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductPreviewResponseDto {
    private Long id;
    private String name;
    private String productPic;
    private String price;
    private List<DiscountResponseDto> discounts;
}