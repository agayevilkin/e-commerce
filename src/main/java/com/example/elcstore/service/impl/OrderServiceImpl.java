package com.example.elcstore.service.impl;

import com.example.elcstore.config.UserInfo;
import com.example.elcstore.domain.Order;
import com.example.elcstore.domain.OrderDetail;
import com.example.elcstore.domain.ProductOption;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.OrderStatus;
import com.example.elcstore.dto.request.OrderDetailRequestDto;
import com.example.elcstore.dto.request.OrderRequestDto;
import com.example.elcstore.dto.response.OrderResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.OrderRepository;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.repository.UserRepository;
import com.example.elcstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    //    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserInfo userInfo;

    @Override
    // TODO: 9/15/2023 Payment pending..
    // TODO: 9/15/2023 write fake Payment service or not use Payment for now
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        User user = userRepository.findById(userInfo.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));
        Order order = mapper.map(requestDto, Order.class);
        order.setOrderDetail(getOrderProductDetailList(requestDto.getOrderDetailRequestDto(), order));
        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.setCustomer(user.getCustomer());
//        order.setPayment();
        return mapper.map(orderRepository.save(order), OrderResponseDto.class);
    }


    @Override
    public OrderResponseDto findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND.getMessage()));
        return mapper.map(order, OrderResponseDto.class);
    }

    @Override
    public void updateOrderStatus(UUID id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND.getMessage()));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(UUID id, OrderRequestDto requestDto) {
        //do not need for now
    }

    @Override
    public void deleteOrder(UUID id) {
        if (checkById(id)) {
            orderRepository.deleteById(id);
        }
    }

    // TODO: 9/15/2023 can be change structure for long process
    private List<OrderDetail> getOrderProductDetailList(List<OrderDetailRequestDto> orderDetailRequestDto, Order order) {
        return orderDetailRequestDto.stream().map((request) -> {
            if (!productRepository.existsById(request.getProductId())) {
                throw new NotFoundException(PRODUCT_NOT_FOUND.getMessage());
            }
            if (!productOptionRepository.existsById(request.getProductOptionId())) {
                throw new NotFoundException(PRODUCT_OPTION_NOT_FOUND.getMessage());
            }
            OrderDetail orderDetail = mapper.map(request, OrderDetail.class);
            orderDetail.setOrder(order);
            return orderDetail;
        }).collect(Collectors.toList());
    }

    private boolean checkById(UUID id) {
        return orderRepository.existsById(id);
    }
}
