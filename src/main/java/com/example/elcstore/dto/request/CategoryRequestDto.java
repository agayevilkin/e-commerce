package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryRequestDto {
    @NotBlank(message = "Please provide a name")
    private String name;

    private UUID parentId;
}
