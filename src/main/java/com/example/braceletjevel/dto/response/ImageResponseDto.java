package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImageResponseDto {
    private UUID id;
    private String imagePath;
}
