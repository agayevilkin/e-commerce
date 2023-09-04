package com.example.elcstore.controller;

import com.example.elcstore.dto.auth.AuthRequest;
import com.example.elcstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        var token = authService.authenticate(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Token", token.getAccessToken());
        headers.set("Refresh-Token", token.getRefreshToken());

        return ResponseEntity.ok().headers(headers).body("authenticated");
    }
}
