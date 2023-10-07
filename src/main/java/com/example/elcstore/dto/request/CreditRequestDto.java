package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreditRequestDto {

    @NotNull(message = "Credit period is required")
    @Positive(message = "Credit period must be positive")
    private Integer creditPeriod;

    @NotNull(message = "Initial payment is required")
    @PositiveOrZero(message = "Initial payment must be positive or zero")
    private Double initialPaymentAmount;

    @NotNull(message = "Interest is required")
    @PositiveOrZero(message = "Initial payment must be positive or zero")
    private Double interest;

    @NotNull(message = "Product id is required")
    private UUID productId;
}
