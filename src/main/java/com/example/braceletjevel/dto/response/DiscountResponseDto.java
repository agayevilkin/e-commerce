package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DiscountResponseDto {
    private UUID id;
    private double currentPrice;
    private int discountPercentage;
}
