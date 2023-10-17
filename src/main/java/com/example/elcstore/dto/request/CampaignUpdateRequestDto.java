package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CampaignUpdateRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    private String note;

    @NotNull(message = "Starting date cannot be null")
    private LocalDateTime startingDate;

    @NotNull(message = "Ending date cannot be null")
    private LocalDateTime endingDate;

    private List<UUID> categoryIds;
}
