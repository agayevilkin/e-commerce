package com.example.elcstore.service;

import com.example.elcstore.domain.enums.OrderStatus;
import com.example.elcstore.dto.request.OrderRequestDto;
import com.example.elcstore.dto.response.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);

    OrderResponseDto findById(UUID id);

    List<OrderResponseDto> getAllOrders();

    List<OrderResponseDto> getAllOrdersByCustomer();

    void updateOrder(UUID id, OrderRequestDto requestDto);

    void deleteOrder(UUID id);

    void updateOrderStatus(UUID id, OrderStatus orderStatus);

}
