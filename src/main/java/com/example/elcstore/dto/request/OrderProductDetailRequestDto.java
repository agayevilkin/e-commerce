package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderProductDetailRequestDto {

    @NotNull
    private UUID productId;
    @NotNull
    private int productPiece;
}
