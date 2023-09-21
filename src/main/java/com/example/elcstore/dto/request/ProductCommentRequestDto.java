package com.example.elcstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductCommentRequestDto {

    @NotBlank(message = "Content is required")
    private String content;

    @NotBlank(message = "authorFullName is required")
    private String authorFullName;

    @NotNull
    @Min(1)
    @Max(5)
    private int star;

    @NotNull
    private UUID productId;
}
