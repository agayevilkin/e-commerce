package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID addressId;

    @NotNull
    private UUID paymentId;
}
