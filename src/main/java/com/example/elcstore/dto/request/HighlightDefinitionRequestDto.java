package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HighlightDefinitionRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
}
