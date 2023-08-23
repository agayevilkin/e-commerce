package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResponseDto {
    private Long id;
    private String currentPrice;
    private String discountPercentage;
}
