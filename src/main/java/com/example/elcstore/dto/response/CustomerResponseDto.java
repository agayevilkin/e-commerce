package com.example.elcstore.dto.response;

import com.example.elcstore.domain.Address;
import com.example.elcstore.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CustomerResponseDto {

    private UUID id;
    private List<AddressResponseDto> address;
}
