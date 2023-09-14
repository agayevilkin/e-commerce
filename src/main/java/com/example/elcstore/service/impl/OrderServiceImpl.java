package com.example.elcstore.service.impl;

import com.example.elcstore.config.UserInfo;
import com.example.elcstore.domain.Address;
import com.example.elcstore.domain.Order;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.OrderStatus;
import com.example.elcstore.dto.request.OrderRequestDto;
import com.example.elcstore.dto.response.OrderResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.AddressRepository;
import com.example.elcstore.repository.OrderRepository;
import com.example.elcstore.repository.UserRepository;
import com.example.elcstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final AddressRepository addressRepository;
    //    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserInfo userInfo;

    @Override
    // TODO: 9/2/2023 complete this after completing Payment and security
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Address address = addressRepository.findById(requestDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND.getMessage()));
        User user = userRepository.findById(userInfo.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));
        System.out.println(user.getEmail());
        System.out.println(user.getCustomer().getId());

        Order order = mapper.map(requestDto, Order.class);
        order.setAddress(address);
//        order.setPayment();
        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.setCustomer(user.getCustomer());
        return mapper.map(repository.save(order), OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto findById(UUID id) {
        Order order = repository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND.getMessage()));
        return mapper.map(order, OrderResponseDto.class);
    }

    @Override
    public void updateOrderStatus(UUID id, OrderStatus orderStatus) {
        Order order = repository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND.getMessage()));
        order.setOrderStatus(orderStatus);
        repository.save(order);
    }

    @Override
    public void updateOrder(UUID id, OrderRequestDto requestDto) {
        //do not need for now
    }

    // TODO: 9/13/2023 add order status update method

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
