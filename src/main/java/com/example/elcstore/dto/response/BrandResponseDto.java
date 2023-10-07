package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BrandResponseDto {
    private UUID id;
    private String brandName;
    private UUID imageId;
}
