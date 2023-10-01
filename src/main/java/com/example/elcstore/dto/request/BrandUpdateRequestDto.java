package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BrandUpdateRequestDto {
    @NotBlank(message = "Please provide a name")
    private String brandName;

    private MultipartFile image;
}
