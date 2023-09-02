package com.example.braceletjevel.controller;

import com.example.braceletjevel.domain.enums.OrderStatus;
import com.example.braceletjevel.dto.request.OrderRequestDto;
import com.example.braceletjevel.dto.response.OrderResponseDto;
import com.example.braceletjevel.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto requestDto) {
        return service.createOrder(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public OrderResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody OrderRequestDto requestDto) {
        service.updateOrder(id, requestDto);
    }

    @PutMapping("/status/{id}")
    @Operation(summary = "updateOrderStatus")
    public void updateOrderStatus(@PathVariable UUID id, @NotNull @RequestParam OrderStatus orderStatus) {
        service.updateOrderStatus(id, orderStatus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteOrder(id);
    }
}
