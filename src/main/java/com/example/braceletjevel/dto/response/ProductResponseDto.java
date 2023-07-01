package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private String price;
    private String imagePath;
    private String info;
}
