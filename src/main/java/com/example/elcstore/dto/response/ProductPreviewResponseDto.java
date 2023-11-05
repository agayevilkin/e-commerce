package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductPreviewResponseDto {
    private UUID id;
    private List<EventResponseDto> events;
    private List<ProductOptionPreviewResponseDto> productOptions;
    private double price;
    private DiscountResponseDto discount;
}