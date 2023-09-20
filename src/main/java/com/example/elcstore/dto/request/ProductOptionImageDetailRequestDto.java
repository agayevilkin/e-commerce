package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ProductOptionImageDetailRequestDto {

    @NotNull
    private MultipartFile image;

    @NotNull
    private int orderNum;
}
