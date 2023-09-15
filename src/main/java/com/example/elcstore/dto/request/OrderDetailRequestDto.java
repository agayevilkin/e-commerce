package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderDetailRequestDto {

    @NotNull
    private UUID productId;
    @NotNull
    private UUID productOptionId;
    @NotNull
    private int quantity;
}
