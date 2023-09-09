package com.example.elcstore.service;

import com.example.elcstore.dto.auth.AuthRequest;
import com.example.elcstore.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(String refreshToken);
}
