package com.agata.bookingapi.service;

import com.agata.bookingapi.dto.BookingDTO;
import com.agata.bookingapi.model.Booking;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.model.enums.BookingStatus;
import com.agata.bookingapi.repository.AuthRepository;
import com.agata.bookingapi.repository.BookingRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final AuthRepository authRepository;

    public BookingService(BookingRepository bookingRepository, AuthRepository authRepository) {
        this.bookingRepository = bookingRepository;
        this.authRepository = authRepository;
    }

    public Booking createBooking(@Valid @RequestBody BookingDTO request) {
        User user = authRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        if (user.isBlocked()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot login because this user is blocked!");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setClientName(request.getClientName());
        booking.setClientPhone(request.getClientPhone());
        booking.setProfessionalName(request.getProfessionalName());
        booking.setService(request.getService());
        booking.setAppointmentTime(request.getAppointmentTime());
        booking.setStatus(BookingStatus.SCHEDULED);

        return bookingRepository.save(booking);

    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDTO request) {
        Booking existsBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found!"));

        if (!existsBooking.getAppointmentTime().equals(request.getAppointmentTime()) &&
                bookingRepository.existsByProfessionalNameAndAppointmentTime(request.getProfessionalName(),
                        request.getAppointmentTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Time already reserved!");
        }

        existsBooking.setAppointmentTime(request.getAppointmentTime());
        return bookingRepository.save(existsBooking);
    }
}