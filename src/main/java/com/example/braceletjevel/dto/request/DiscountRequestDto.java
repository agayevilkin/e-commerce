package com.example.braceletjevel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountRequestDto {

    private Long productId;
    private String currentPrice;
    private String discountPercentage;

}
