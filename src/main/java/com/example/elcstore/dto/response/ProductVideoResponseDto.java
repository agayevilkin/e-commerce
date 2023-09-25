package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductVideoResponseDto {

    private UUID id;
    private String title;
    private String description;
    private String videoLink;
    private String videoThumbnail;
}
