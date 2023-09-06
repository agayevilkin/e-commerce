package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.StockStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class ProductRequestDto {

    @Size(min = 5, message = "Your user name must have at least 5 characters")
    @NotBlank(message = "Please provide a name")
    private String name;

    @Size(min = 5, message = "Your user name must have at least 5 characters")
    @NotBlank(message = "Please provide a title")
    private String title;

    @NotNull
    private double price;

    @Size(min = 5, message = "Your user name must have at least 5 characters")
    @NotBlank(message = "Please provide a createdBy")
    private String createdBy;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID colorId;

    @NotNull
    private UUID brandId;
}
