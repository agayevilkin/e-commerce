package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RatingRequestDto {
    @Size(min = 1, max = 5)
    private int star;

    @NotNull
    private UUID productId;
}
