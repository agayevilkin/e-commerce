package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class CampaignCreateRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    private String note;

    @NotNull(message = "Starting date cannot be null")
    private LocalDateTime startingDate;

    @NotNull(message = "Ending date cannot be null")
    private LocalDateTime endingDate;

    @NotNull
    private MultipartFile image;
}
