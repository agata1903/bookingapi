package com.agata.bookingapi.controller;

import com.agata.bookingapi.dto.LoginRequest;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.repository.AuthRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthRepository authRepository;

    public AuthController(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @PostMapping("/signup")
    public User signUp(@Valid @RequestBody User auth) {
        if (authRepository.findByEmail(auth.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists!");
        }
        return authRepository.save(auth);
    }

    @PostMapping("/login")
    public User Login(@RequestBody LoginRequest request) {
        User auth = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found!"));
        if (auth.isBlocked()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied for blocked account!");
        }
        authRepository.save(auth);

        if (!request.getPassword().equals(auth.getPassword())) {
            auth.setChances(auth.getChances() - 1);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password, try again!");
        }

        if (auth.getChances() == 0) {
            auth.setBlocked(true);
            authRepository.save(auth);
        }
        auth.setChances(3);
        return authRepository.save(auth);
    }
}
