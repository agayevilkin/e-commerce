package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductOptionAdminPreviewResponseDto {

    private UUID id;
    private String title;
    private UUID thumbnailId;
}
