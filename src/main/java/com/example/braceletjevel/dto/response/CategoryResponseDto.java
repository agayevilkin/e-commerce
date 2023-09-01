package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private UUID id;
    private String name;
}
