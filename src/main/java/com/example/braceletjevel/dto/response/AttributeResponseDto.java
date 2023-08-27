package com.example.braceletjevel.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeResponseDto {
    private Long id;
    private String name;
    private String value;
}
