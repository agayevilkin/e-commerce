package com.example.braceletjevel.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductCommentResponseDto {
    private UUID id;
    private String content;
    private String commentAuthor;
    private LocalDateTime localDateTime;
}
