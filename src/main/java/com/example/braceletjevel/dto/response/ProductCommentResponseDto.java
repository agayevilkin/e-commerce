package com.example.braceletjevel.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductCommentResponseDto {
    private Long id;
    private String content;
    private String commentAuthor;
    private LocalDateTime localDateTime;
}
