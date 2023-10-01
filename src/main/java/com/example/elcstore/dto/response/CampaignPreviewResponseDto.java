package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class CampaignPreviewResponseDto {

    private UUID id;
    private String title;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private UUID thumbnailImageId;
}
