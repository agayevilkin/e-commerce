package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.StockStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductOptionUpdateRequestDto {

    @NotBlank(message = "title is required")
    private String title;

    @NotNull
    private StockStatus stockStatus;

    @NotNull
    private UUID colorId;

    @NotNull
    private UUID productId;

    private List<UUID> events;
}
