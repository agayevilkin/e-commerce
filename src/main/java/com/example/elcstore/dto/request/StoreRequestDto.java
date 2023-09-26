package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.StoreLocationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must be at most 500 characters")
    private String address;

    @NotBlank(message = "Working hours are required")
    @Size(max = 100, message = "Working hours must be at most 100 characters")
    private String workingHours;

    @NotNull(message = "Store location status is required")
    private StoreLocationStatus storeLocationStatus;
}
