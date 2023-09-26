package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.VideoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductYouTubeRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @NotBlank(message = "Video link is required")
    private String videoLink;

    @NotNull(message = "Video status is required")
    private VideoStatus videoStatus;

    private Boolean isNew;
}
