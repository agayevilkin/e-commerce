package com.example.braceletjevel.dto.request;

import com.example.braceletjevel.domain.enums.StockStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

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
    private StockStatus stockStatus;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID colorId;

    @NotNull
    private UUID brandId;
}
