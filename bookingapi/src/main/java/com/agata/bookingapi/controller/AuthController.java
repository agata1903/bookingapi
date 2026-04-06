package com.agata.bookingapi.controller;

import com.agata.bookingapi.dto.LoginRequest;
import com.agata.bookingapi.dto.UserDTO;
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
    public User signUp(@Valid @RequestBody UserDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return authRepository.save(user);
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
            if (auth.getChances() <= 0) {
                auth.setBlocked(true);
                authRepository.save(auth);
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account blocked!");
            }
            authRepository.save(auth);

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password. Remaining chances: " + auth.getChances());
        }
        auth.setChances(3);
        return authRepository.save(auth);
    }
}
