package com.example.elcstore.service.impl;

import com.example.elcstore.config.security.GenericUserPrincipal;
import com.example.elcstore.config.security.JwtService;
import com.example.elcstore.config.security.MyUserPrincipal;
import com.example.elcstore.dto.auth.AuthRequest;
import com.example.elcstore.dto.auth.AuthResponse;
import com.example.elcstore.exception.InvalidTokenException;
import com.example.elcstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;


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
        GenericUserPrincipal userPrincipal = GenericUserPrincipal
                .builder()
                .authorities(myUserPrincipal.getAuthorities())
                .userId(myUserPrincipal.getUserId())
                .username(myUserPrincipal.getUsername())
                .build();

        return jwtService.generateToken(userPrincipal);
    }

    public AuthResponse refreshToken(String refreshToken) {

        final String jwt;
        final String username;

        if (!refreshToken.startsWith("Bearer ")) {
            log.trace("The token signature is invalid.");
            throw new InvalidTokenException("The token signature is invalid.");  // okTODO handle
        }

        jwt = refreshToken.substring(7);

        try {
            username = jwtService.extractUsername(jwt);
        } catch (RuntimeException e) {
            log.trace("Invalid Token!:", e);
            throw new InvalidTokenException(e.getMessage());
        }

        if (username != null) {
            if (jwtService.isTokenValid(jwt)) {

                MyUserPrincipal userDetails = (MyUserPrincipal) userDetailService.loadUserByUsername(username);
                GenericUserPrincipal userPrincipal = GenericUserPrincipal
                        .builder()
                        .authorities(userDetails.getAuthorities())
                        .userId(userDetails.getUserId())
                        .username(userDetails.getUsername())
                        .build();
                return jwtService.generateToken(userPrincipal);

            } else {
                log.trace("Token expired!");
                throw new InvalidTokenException("Token expired!");
            }
        }
        return null;
    }

}
