package com.example.braceletjevel.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DiscountRequestDto {
    @NotNull
    private UUID productId;

    @NotNull
    private double currentPrice;
}
