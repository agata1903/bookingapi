package com.agata.bookingapi.repository;

import com.agata.bookingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByEmail(String email);
    @Override
    boolean existsById(Long aLong);
}
