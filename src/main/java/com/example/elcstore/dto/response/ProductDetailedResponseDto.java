package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductDetailedResponseDto {

    private UUID id;
    private String name;
    private double price;
    private DiscountResponseDto discount;
    private HighlightResponseDto highlight;
    private List<EventResponseDto> events;
    private List<ProductOptionDetailedResponseDto> productOptions;
}