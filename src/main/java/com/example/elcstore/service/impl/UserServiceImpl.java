package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Customer;
import com.example.elcstore.domain.Employee;
import com.example.elcstore.domain.Role;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.StateStatus;
import com.example.elcstore.domain.enums.UserStatus;
import com.example.elcstore.dto.request.UserCustomerRequestDto;
import com.example.elcstore.dto.request.UserEmployeeRequestDto;
import com.example.elcstore.dto.response.UserResponseDto;
import com.example.elcstore.exception.AlreadyExistsException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.RoleRepository;
import com.example.elcstore.repository.UserRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.RoleService;
import com.example.elcstore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ImageService imageService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void createEmployeeUser(UserEmployeeRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail()))
            throw new AlreadyExistsException("This email address is already in use. Please try another.");
        User user = mapper.map(requestDto, User.class);
        Employee employee = mapper.map(requestDto, Employee.class);
        user.setRoles(getDefaultRole());
        //todo To make it INACTIVE when email verification is configured.
        user.setStateStatus(StateStatus.ACTIVE);
        user.setUserStatus(UserStatus.EMPLOYEE);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setEmployee(employee);
        if (requestDto.getImage() != null) employee.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        userRepository.save(user);
    }

    @Override
    public void createCustomerUser(UserCustomerRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail()))
            throw new AlreadyExistsException("This email address is already in use. Please try another.");
        User user = mapper.map(requestDto, User.class);
        Customer customer = mapper.map(requestDto, Customer.class);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setCustomer(customer);
        user.setRoles(getDefaultRole());
        //todo To make it INACTIVE when email verification is configured.
        user.setStateStatus(StateStatus.ACTIVE);
        user.setUserStatus(UserStatus.CUSTOMER);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        if (user.getEmployee() != null && user.getEmployee().getImageId() != null) {
            userResponseDto.setAvatar(imageService.createImageUrl(user.getEmployee().getImageId()));
        }
        return userResponseDto;
    }

    @Override
    @Transactional
    public void updateUser(UUID id, UserEmployeeRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        Employee employee = user.getEmployee();
        if (requestDto.getImage() != null)
            employee.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        mapper.map(requestDto, user);
        mapper.map(requestDto, employee);
        user.setRoles(getRoleList(requestDto.getRoles()));
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setEmployee(employee);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        userRepository.delete(user);
        if (user.getEmployee() != null && user.getEmployee().getImageId() != null) {
            imageService.deleteImage(user.getEmployee().getImageId());
        }
    }

    private List<Role> getDefaultRole() {
        return Collections.singletonList(roleService.getDefaultRole());
    }

    private List<Role> getRoleList(List<UUID> roles) {
        return roles
                .stream()
                .map(roleService::findById)
                .collect(Collectors.toList());
    }
}
