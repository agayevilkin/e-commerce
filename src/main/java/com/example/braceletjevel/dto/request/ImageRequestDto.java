package com.example.braceletjevel.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequestDto {
    private Long productId;
    private MultipartFile file;
}
