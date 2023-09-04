package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.District;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AddressRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Phone is required")
    @Size(min = 12, max = 19)
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Address title is required")
    @Size(min = 5, max = 45)
    private String addressTitle;

    @NotNull(message = "District is required")
    private District district;

    @NotNull(message = "Customer is required")
    private UUID customerId;
}
