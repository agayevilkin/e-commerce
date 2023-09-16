package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
public class ProductImageDetailRequestDto {
    @NotNull
    private MultipartFile image;

    @NotNull
    private UUID productOptionId;

    @NotNull
    private int orderNum;
}
