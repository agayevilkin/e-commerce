package com.example.elcstore.service.impl;

import com.example.elcstore.config.security.JwtService;
import com.example.elcstore.config.security.MyUserPrincipal;
import com.example.elcstore.dto.auth.AuthRequest;
import com.example.elcstore.dto.auth.AuthResponse;
import com.example.elcstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        log.info("successfully authenticate!");
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authenticate.getPrincipal();
        System.out.println(authenticate.getPrincipal());
        return jwtService.generateToken(myUserPrincipal);
    }
}
