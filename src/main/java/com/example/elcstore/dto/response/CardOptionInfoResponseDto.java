package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CardOptionInfoResponseDto {

    private UUID id;
    private String title;
    private String description;
    private UUID imageId;
}
