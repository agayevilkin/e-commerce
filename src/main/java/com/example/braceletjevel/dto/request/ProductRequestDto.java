package com.example.braceletjevel.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequestDto {

    private String name;
    private String description;
    private String price;
    private String info;
    private MultipartFile file;
}
