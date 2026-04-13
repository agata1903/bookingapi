package com.agata.bookingapi.controller;

import com.agata.bookingapi.dto.LoginRequest;
import com.agata.bookingapi.dto.UserDTO;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public User signUp(@Valid @RequestBody UserDTO request) {
        return authService.signUp(request);
    }

    @PostMapping
    public User Login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
