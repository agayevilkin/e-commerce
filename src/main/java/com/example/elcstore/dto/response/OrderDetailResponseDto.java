package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderDetailResponseDto {

    private UUID id;
    private UUID productId;
    private UUID productOptionId;
    private int quantity;
}
