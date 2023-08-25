package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.enums.Categories;
import com.example.braceletjevel.dto.request.ImageRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String name;
    private String price;
    private LocalDateTime createDate;
    private Categories categories;
    private List<DiscountResponseDto> discounts;
    private RatingResponseDto rating;
    private List<ImageResponseDto> images;
}