package com.nikhil.taskapi.controller;

import com.nikhil.taskapi.dto.AuthResponse;
import com.nikhil.taskapi.dto.LoginRequest;
import com.nikhil.taskapi.dto.RegisterRequest;
import com.nikhil.taskapi.security.TokenBlacklistService;
import com.nikhil.taskapi.security.JwtUtil;
import com.nikhil.taskapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        long expiry = jwtUtil.getExpirationMillis(token);
        tokenBlacklistService.blacklistToken(token, expiry);
        return ResponseEntity.ok("Logged out successfully");
    }
}