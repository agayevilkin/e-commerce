package com.example.braceletjevel.service;

import com.example.braceletjevel.domain.enums.OrderStatus;
import com.example.braceletjevel.dto.request.OrderRequestDto;
import com.example.braceletjevel.dto.response.OrderResponseDto;

import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);

    OrderResponseDto findById(UUID id);

    void updateOrder(UUID id, OrderRequestDto requestDto);

    void deleteOrder(UUID id);

    void updateOrderStatus(UUID id, OrderStatus orderStatus);
}
