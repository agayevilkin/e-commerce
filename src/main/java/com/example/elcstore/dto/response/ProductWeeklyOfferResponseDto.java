package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductWeeklyOfferResponseDto {

    private UUID id;
    private List<EventResponseDto> events;
    private BrandResponseDto brand;
    private double price;
    private DiscountResponseDto discount;
}
