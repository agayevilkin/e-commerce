package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductOptionCategoryBannerResponseDto {
    private UUID id;
    private String title;
    private UUID thumbnailId;
    private UUID productId;
    private UUID brandImageId;;
    // TODO: 10/2/2023  monthly price

}
