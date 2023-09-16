package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.City;
import com.example.elcstore.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class OrderResponseDto {

    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String description;
    private City city;
    private String address;
    private CustomerResponseDto customer;
    private double totalPrice;
    private List<OrderDetailResponseDto> orderDetail;
    private OrderStatus orderStatus;

}
