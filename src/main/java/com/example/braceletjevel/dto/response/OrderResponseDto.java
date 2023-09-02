package com.example.braceletjevel.dto.response;

import com.example.braceletjevel.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class OrderResponseDto {

    private UUID id;
    private OrderStatus orderStatus;
    private CustomerResponseDto customer;
    private AddressResponseDto address;
    private PaymentResponseDto payment;
    private List<OrderProductDetailResponseDto> orderProductDetails;
}
