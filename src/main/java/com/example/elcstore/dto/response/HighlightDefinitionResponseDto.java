package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class HighlightDefinitionResponseDto {
    private UUID id;
    private String name;
}
