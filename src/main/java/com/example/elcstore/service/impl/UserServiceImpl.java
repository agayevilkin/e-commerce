package com.example.elcstore.service.impl;

import com.example.elcstore.domain.EmployeeAtribute;
import com.example.elcstore.domain.Role;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.Status;
import com.example.elcstore.dto.request.UserCustomerRequestDto;
import com.example.elcstore.dto.request.UserEmployeeRequestDto;
import com.example.elcstore.dto.response.UserResponseDto;
import com.example.elcstore.exception.AlreadyExistsException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.UserRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    //    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public void createEmployeeUser(UserEmployeeRequestDto requestDto) {
        if (repository.existsByUsername(requestDto.getUsername())) {
            throw new AlreadyExistsException("username already exists!");
        }
        User user = mapper.map(requestDto, User.class);
        EmployeeAtribute employeeAtribute = mapper.map(requestDto, EmployeeAtribute.class);
        user.setRoles(getUserList(requestDto.getRoles()));
        user.setStatus(Status.ACTIVE);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setEmployeeAtribute(employeeAtribute);
        repository.save(user);
    }

    @Override
    public void createCustomerUser(UserCustomerRequestDto requestDto) {
        if (repository.existsByUsername(requestDto.getUsername())) {
            throw new AlreadyExistsException("username already exists!");
        }
        User user = mapper.map(requestDto, User.class);
        user.setPassword(encoder.encode(requestDto.getPassword()));
//        user.setRoles(new ArrayList<>(){""});
        user.setStatus(Status.ACTIVE);
        repository.save(user);
    }

    @Override
    public UserResponseDto findById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        if (user.getEmployeeAtribute() != null && user.getEmployeeAtribute().getImageId() != null) {
            userResponseDto.setAvatar(imageService.createImageUrl(user.getEmployeeAtribute().getImageId()));
        }
        return userResponseDto;
    }

    @Override
    @Transactional
    public void updateUser(UUID id, UserEmployeeRequestDto requestDto) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        EmployeeAtribute employeeAtribute = user.getEmployeeAtribute();
        if (requestDto.getImage() != null)
            employeeAtribute.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        mapper.map(requestDto, user);
        mapper.map(requestDto, employeeAtribute);
        user.setRoles(getUserList(requestDto.getRoles()));
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setEmployeeAtribute(employeeAtribute);
        repository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        repository.delete(user);
        if (user.getEmployeeAtribute() != null && user.getEmployeeAtribute().getImageId() != null) {
            imageService.deleteImage(user.getEmployeeAtribute().getImageId());
        }
    }

    private List<Role> getUserList(List<UUID> roles) {
//        return roles.stream()
//                .map(r -> roleRepository.findById(r).orElseThrow(() -> new NotFoundException("Role not found"))).collect(Collectors.toList());
        return null;
    }
}
