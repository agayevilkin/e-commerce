package com.example.braceletjevel.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductRequestDto {

    private String name;
    private String title;
    private String price;
    private LocalDateTime createDate;
//    private Categories categories;
    private Long rating;
}
