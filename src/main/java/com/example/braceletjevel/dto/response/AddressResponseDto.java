package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.enums.District;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AddressResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String addressTitle;
    private District district;
    private CustomerResponseDto customer;
}
