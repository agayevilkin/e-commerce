package com.example.elcstore.service;

import com.example.elcstore.domain.Role;

import java.util.UUID;

public interface RoleService {

    Role getDefaultRole();

    Role findById(UUID id);
}
