package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RatingResponseDto {
    private UUID id;
    private int star;
}
