package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Role;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.RoleRepository;
import com.example.elcstore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private static final String USER = "USER";
    private final RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName(USER).orElseThrow(() -> new NotFoundException("Role not found!"));
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found!"));
    }
}
