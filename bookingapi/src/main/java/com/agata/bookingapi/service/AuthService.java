package com.agata.bookingapi.service;

import com.agata.bookingapi.dto.LoginRequest;
import com.agata.bookingapi.dto.UserDTO;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.repository.AuthRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public User signUp(@Valid @RequestBody UserDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return authRepository.save(user);
    }

    public User login(@Valid @RequestBody LoginRequest loginRequest) {
        User auth = authRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found!"));

        if (auth.isBlocked()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot login! User is blocked!");
        }

        if (!auth.getPassword().equals(loginRequest.getPassword())) {
            auth.setChances(auth.getChances() - 1);

            if (auth.getChances() <= 0) {
                auth.setBlocked(true);
            }

            authRepository.save(auth);

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password! Remaining chances: "
                    + auth.getChances());
        }

        auth.setChances(3);
        return authRepository.save(auth);
    }
}
