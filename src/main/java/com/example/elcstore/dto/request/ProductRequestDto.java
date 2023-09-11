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

    @Size(min = 5, message = "Your user name must have at least 5 characters")
    @NotBlank(message = "Please provide a name")
    private String name;

    @NotNull
    private double price;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID brandId;

    private List<UUID> highlight;

    private List<UUID> technicalCharacteristics;
}
