package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 5)
    private int star;

    @NotNull
    private UUID productId;
}
