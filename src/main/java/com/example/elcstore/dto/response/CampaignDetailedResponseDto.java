package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CampaignDetailedResponseDto {

    private UUID id;
    private String title;
    private String description;
    private String note;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private UUID imageId;
    private List<CategoryResponseDto> categories;
}
