package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CampaignPreviewResponseDto {

    private UUID id;
    private String title;
    private String startingDate;
    private String endingDate;
    private UUID thumbnailImageId;
}
