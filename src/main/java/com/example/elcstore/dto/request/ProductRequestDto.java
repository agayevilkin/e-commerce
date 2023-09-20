package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductRequestDto {

    @NotBlank(message = "Please provide a name")
    private String name;

    @NotNull
    private double price;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID brandId;

    private UUID highlightId;

    private List<UUID> events;

    private List<UUID> technicalCharacteristics;

    // TODO: 9/18/2023 can be add HighLightRequestDto dto for highlight create
}
