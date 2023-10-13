package com.example.elcstore.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String username;
    private String password;
}
