package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Role;
import com.example.elcstore.domain.SystemUser;
import com.example.elcstore.domain.enums.Status;
import com.example.elcstore.dto.request.SystemUserRequestDto;
import com.example.elcstore.dto.response.SystemUserResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.SystemUserRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.SystemUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository repository;
    //    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;
    //todo comment-out after configure spring security
//    @Autowired
//    private PasswordEncoder encoder;

    @Override
    @Transactional
    public void createSystemUser(SystemUserRequestDto requestDto) {
        SystemUser systemUser = mapper.map(requestDto, SystemUser.class);
        systemUser.setRoles(getUserList(requestDto.getRoles()));
        systemUser.setStatus(Status.ACTIVE);
//        systemUser.setPassword(encoder.encode(requestDto.getPassword()));
        if (requestDto.getImage() != null) {
            systemUser.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        } else {
            systemUser.setImageId(UUID.fromString("24312042-47e8-42a1-83a4-59dff7a563b3"));
        }
        repository.save(systemUser);
    }


    @Override
    public SystemUserResponseDto findById(UUID id) {
        SystemUser systemUser = repository.findById(id).orElseThrow(() -> new NotFoundException("System User not found!"));
        SystemUserResponseDto systemUserResponseDto = mapper.map(systemUser, SystemUserResponseDto.class);
        //todo can be change
        systemUserResponseDto.setAvatar(imageService.createImageUrl(systemUser.getImageId()));
        return systemUserResponseDto;
    }

    @Override
    @Transactional
    public void updateSystemUser(UUID id, SystemUserRequestDto requestDto) {
        SystemUser systemUser = repository.findById(id).orElseThrow(() -> new NotFoundException("System User not found!"));
        mapper.map(requestDto, systemUser);
        systemUser.setRoles(getUserList(requestDto.getRoles()));
//        systemUser.setPassword(encoder.encode(requestDto.getPassword()));
        if (requestDto.getImage() != null)
            systemUser.setImageId(imageService.uploadImage(requestDto.getImage()).getId());
        repository.save(systemUser);
    }

    @Override
    @Transactional
    public void deleteSystemUser(UUID id) {
        SystemUser systemUser = repository.findById(id).orElseThrow(() -> new NotFoundException("System User not found!"));
        repository.delete(systemUser);
        imageService.deleteImage(systemUser.getImageId());
    }

    private List<Role> getUserList(List<UUID> roles) {
//        return roles.stream()
//                .map(r -> roleRepository.findById(r).orElseThrow(() -> new NotFoundException("Role not found"))).collect(Collectors.toList());
        return null;
    }
}
