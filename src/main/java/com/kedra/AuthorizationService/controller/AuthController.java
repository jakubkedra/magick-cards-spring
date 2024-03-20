package com.kedra.AuthorizationService.controller;

import com.kedra.AuthorizationService.dto.AuthResponseDto;
import com.kedra.AuthorizationService.service.AuthService;
import com.kedra.AuthorizationService.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto.getUsername(), registerDto.getPassword());
    }

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody RegisterDto registerDto) {
        return authService.login(registerDto.getUsername(), registerDto.getPassword());
    }
}
