package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BrandCreateRequestDto {
    @NotBlank(message = "Please provide a name")
    private String name;

    @NotNull(message = "File cannot be null")
    private MultipartFile image;
}
