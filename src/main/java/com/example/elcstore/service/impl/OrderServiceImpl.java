package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Address;
import com.example.elcstore.domain.Order;
import com.example.elcstore.domain.enums.OrderStatus;
import com.example.elcstore.dto.request.OrderRequestDto;
import com.example.elcstore.dto.response.OrderResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.AddressRepository;
import com.example.elcstore.repository.OrderRepository;
import com.example.elcstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
