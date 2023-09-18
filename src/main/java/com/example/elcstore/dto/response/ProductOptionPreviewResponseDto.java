package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.StockStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductOptionPreviewResponseDto {

    private UUID id;
    private String title;
    private StockStatus stockStatus;
    private ColorResponseDto color;
    private UUID thumbnailId;

}
