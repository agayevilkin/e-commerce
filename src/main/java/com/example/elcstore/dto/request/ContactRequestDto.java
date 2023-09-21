package com.example.elcstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ContactRequestDto {

    @NotBlank(message = "fullName is required")
    private String fullName;

    @NotBlank(message = "phoneNumber is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must contain only digits and optional leading '+'")
    private String phoneNumber;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "company is required")
    private String company;

    @NotBlank(message = "message is required")
    private String message;

    @NotBlank(message = "callTimeInterval is required")
    private String callTimeInterval;

}
