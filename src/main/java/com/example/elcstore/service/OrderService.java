package com.example.elcstore.service;

import com.example.elcstore.domain.enums.OrderStatus;
import com.example.elcstore.dto.request.OrderRequestDto;
import com.example.elcstore.dto.response.OrderResponseDto;

import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);

    OrderResponseDto findById(UUID id);

    void updateOrder(UUID id, OrderRequestDto requestDto);

    void deleteOrder(UUID id);

    void updateOrderStatus(UUID id, OrderStatus orderStatus);
}
