package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductCommentRequestDto {

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull
    private UUID productId;
}
