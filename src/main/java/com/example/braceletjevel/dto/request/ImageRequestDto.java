package com.example.braceletjevel.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
public class ImageRequestDto {
    private UUID productId;
    private MultipartFile file;
}
