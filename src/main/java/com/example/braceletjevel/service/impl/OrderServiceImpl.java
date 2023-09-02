package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Address;
import com.example.braceletjevel.domain.Order;
import com.example.braceletjevel.domain.enums.OrderStatus;
import com.example.braceletjevel.dto.request.OrderRequestDto;
import com.example.braceletjevel.dto.response.OrderResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.AddressRepository;
import com.example.braceletjevel.repository.OrderRepository;
import com.example.braceletjevel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final AddressRepository addressRepository;
    //    private final PaymentRepository paymentReposi;
    private final ModelMapper mapper;

    @Override
    // TODO: 9/2/2023 complete this after completing Payment and security
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Address address = addressRepository.findById(requestDto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Address not found!"));
        Order order = mapper.map(requestDto, Order.class);
        order.setAddress(address);
//        order.setPayment();
        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
//        order.setCreatedBy("userinfo.name");
//        order.setCustomer();  //check user
        return mapper.map(repository.save(order), OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto findById(UUID id) {
        Order order = repository.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));
        return mapper.map(order, OrderResponseDto.class);
    }

    @Override
    public void updateOrderStatus(UUID id, OrderStatus orderStatus) {
        Order order = repository.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));
        order.setOrderStatus(orderStatus);
        repository.save(order);
    }

    @Override
    public void updateOrder(UUID id, OrderRequestDto requestDto) {
        //do not need for now
    }

    @Override
    public void deleteOrder(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }


    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }
}
