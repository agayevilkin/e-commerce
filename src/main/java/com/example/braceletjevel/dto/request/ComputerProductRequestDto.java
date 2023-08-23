package com.example.braceletjevel.dto.request;

import com.example.braceletjevel.domain.enums.Categories;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ComputerProductRequestDto {

    private String detailName;
    private String title;
    private String price;
    private LocalDateTime createDate;
    private Categories categories;
    private Long rating;
}
