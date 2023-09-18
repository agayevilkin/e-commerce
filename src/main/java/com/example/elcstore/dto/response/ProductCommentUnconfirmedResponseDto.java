package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.CommentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class ProductCommentUnconfirmedResponseDto {
    private UUID id;
    private UUID productId;
    private String content;
    private int star;
    private String authorFullName;
    private CommentStatus commentStatus;
    private LocalDateTime createdDate;
}
