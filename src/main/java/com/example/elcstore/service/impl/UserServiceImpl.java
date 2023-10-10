package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Customer;
import com.example.elcstore.domain.Employee;
import com.example.elcstore.domain.Role;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.UserAccountStateStatus;
import com.example.elcstore.domain.enums.UserStatus;
import com.example.elcstore.dto.request.UserCustomerRequestDto;
import com.example.elcstore.dto.request.UserEmployeeRequestDto;
import com.example.elcstore.dto.response.UserResponseDto;
import com.example.elcstore.exception.AlreadyExistsException;
import com.example.elcstore.exception.NotFoundException;
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

import static com.example.elcstore.domain.enums.AuthProvider.local;
import static com.example.elcstore.exception.messages.AlreadyExistsExceptionMessages.USERNAME_ALREADY_EXISTS;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.USER_NOT_FOUND;

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
    // TODO: 10/9/2023 can be add email field and email verification
    public void createEmployeeUser(UserEmployeeRequestDto requestDto) {
        checkAndThrowIfUsernameExists(requestDto.getUsername());
        User user = mapper.map(requestDto, User.class);
        Employee employee = mapper.map(requestDto, Employee.class);
        user.setRoles(getDefaultRole());
        user.setUserAccountStateStatus(UserAccountStateStatus.ACTIVE);
        user.setUserStatus(UserStatus.EMPLOYEE);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setEmployee(employee);
        user.setAuthProvider(local);
        if (requestDto.getImage() != null) {
            UUID imageId = imageService.uploadImage(requestDto.getImage()).getId();
            employee.setImageId(imageId);
            employee.setProfilePic(imageService.createImageUrl(imageId));
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    // TODO: 10/9/2023 can be add email field and email verification
    public void createCustomerUser(UserCustomerRequestDto requestDto) {
        checkAndThrowIfUsernameExists(requestDto.getUsername());
        User user = mapper.map(requestDto, User.class);
        Customer customer = mapper.map(requestDto, Customer.class);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setCustomer(customer);
        user.setRoles(getDefaultRole());
        user.setUserAccountStateStatus(UserAccountStateStatus.ACTIVE);
        user.setUserStatus(UserStatus.CUSTOMER);
        user.setAuthProvider(local);
        if (requestDto.getImage() != null) {
            UUID imageId = imageService.uploadImage(requestDto.getImage()).getId();
            customer.setImageId(imageId);
            customer.setProfilePic(imageService.createImageUrl(imageId));
        }
        userRepository.save(user);
    }


    @Override
    // TODO: 10/9/2023 simplify this code or create 2 difference response dto for each user
    public UserResponseDto findById(UUID id) {
        User user = getUserById(id);
        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        responseDto.setProfilePic(user.getCustomer().getProfilePic());
        return responseDto;
    }

    // TODO: 9/26/2023 add update method for CUSTOMER or add email verification method
    @Override
    @Transactional
    public void updateEmployeeUser(UUID id, UserEmployeeRequestDto requestDto) {
        checkAndThrowIfUsernameExists(requestDto.getUsername());
        User user = getUserById(id);
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
        User user = getUserById(id);
        userRepository.delete(user);
        if (user.getEmployee() != null && user.getEmployee().getImageId() != null) {
            imageService.deleteImage(user.getEmployee().getImageId());
        } else if (user.getCustomer() != null && user.getCustomer().getImageId() != null) {
            imageService.deleteImage(user.getCustomer().getImageId());
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

    public void checkAndThrowIfUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyExistsException(USERNAME_ALREADY_EXISTS.getMessage());
        }
    }

    private User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));
    }
}
