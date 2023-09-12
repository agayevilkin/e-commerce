package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class CampaignResponseDto {

    private UUID id;
    private String title;
    private String description;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String imagePath;
}
