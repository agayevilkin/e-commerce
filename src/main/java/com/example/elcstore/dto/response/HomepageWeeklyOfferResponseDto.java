package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class HomepageWeeklyOfferResponseDto {

    private UUID id;
    private LocalDateTime deadline;
    private ProductOptionWeeklyOfferResponseDto productOption;
}
