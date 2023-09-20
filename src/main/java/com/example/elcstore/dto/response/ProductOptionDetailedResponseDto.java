package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.StockStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductOptionDetailedResponseDto {

    private UUID id;
    private String title;
    private StockStatus stockStatus;
    private ColorResponseDto color;
    private List<ProductImageDetailResponseDto> imageDetails;

}
