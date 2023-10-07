package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreditResponseDto {

    private UUID id;
    private Double monthlyPrice;
    private Integer creditPeriod;
    private Double currentPrice;
    private Double initialPayment;
}
