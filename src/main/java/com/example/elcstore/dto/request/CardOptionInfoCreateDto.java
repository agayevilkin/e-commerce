package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class CardOptionInfoCreateDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull
    private MultipartFile image;
}
