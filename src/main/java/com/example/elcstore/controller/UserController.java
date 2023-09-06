package com.example.elcstore.controller;

import com.example.elcstore.dto.request.UserCustomerRequestDto;
import com.example.elcstore.dto.request.UserEmployeeRequestDto;
import com.example.elcstore.dto.response.UserResponseDto;
import com.example.elcstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "createEmployeeUser")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public void create(@Valid @RequestBody UserEmployeeRequestDto requestDto) {
        service.createEmployeeUser(requestDto);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "createCustomerUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void create(@Valid @RequestBody UserCustomerRequestDto requestDto) {
        service.createCustomerUser(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public UserResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void update(@PathVariable UUID id, @Valid @RequestBody UserEmployeeRequestDto requestDto) {
        service.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void delete(@PathVariable UUID id) {
        service.deleteUser(id);
    }
}
