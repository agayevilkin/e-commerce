package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductPreviewResponseDto {
    private UUID id;
    private String name;
    private String productPic;
    private double price;
    private DiscountResponseDto discount;
}