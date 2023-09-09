package com.example.elcstore.controller;

import com.example.elcstore.dto.auth.AuthRequest;
import com.example.elcstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@PreAuthorize("permitAll()")
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

    @PutMapping
    public ResponseEntity<?> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        var token = authService.refreshToken(refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Token", token.getAccessToken());
        headers.set("Refresh-Token", token.getRefreshToken());

        return ResponseEntity.ok().headers(headers).body("");
    }
}
