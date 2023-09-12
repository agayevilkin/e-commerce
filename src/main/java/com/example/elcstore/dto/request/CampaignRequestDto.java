package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class CampaignRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Starting date cannot be null")
    private LocalDate startingDate;

    @NotNull(message = "Ending date cannot be null")
    private LocalDate endingDate;

    private MultipartFile image;
}
