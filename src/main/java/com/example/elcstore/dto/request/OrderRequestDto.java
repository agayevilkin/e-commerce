package com.example.elcstore.dto.request;

import com.example.elcstore.domain.enums.City;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequestDto {

    @NotEmpty(message = "fullName is Required")
    private String fullName;

    @NotBlank(message = "phoneNumber is Required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must contain only digits and optional leading '+'")
    private String phoneNumber;

    @Email
    @NotBlank(message = "email is Required")
    private String email;

    @NotBlank(message = "description is Required")
    private String description;

    @NotNull
    private double totalPrice;

    @NotNull
    private City city;

    @NotEmpty(message = "address is Required")
    private String address;

    private List<OrderDetailRequestDto> orderDetailRequestDto;

    //    @NotNull
//    private UUID paymentId;
}
