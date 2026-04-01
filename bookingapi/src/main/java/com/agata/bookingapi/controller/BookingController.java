package com.agata.bookingapi.controller;

import com.agata.bookingapi.dto.BookingDTO;
import com.agata.bookingapi.model.Booking;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.model.enums.BookingStatus;
import com.agata.bookingapi.repository.AuthRepository;
import com.agata.bookingapi.repository.BookingRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final AuthRepository authRepository;

    public BookingController(BookingRepository bookingRepository, AuthRepository authRepository) {
        this.bookingRepository = bookingRepository;
        this.authRepository = authRepository;
    }

    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingDTO request) {
        User user = authRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        Booking booking = new Booking();
        
        booking.setUser(user);
        booking.setClientName(request.getClientName());
        booking.setClientPhone(request.getClientPhone());
        booking.setAppointmentTime(request.getAppointmentTime());
        booking.setService(request.getService());
        booking.setStatus(BookingStatus.SCHEDULED);
        booking.setProfessionalName(request.getProfessionalName());

        return bookingRepository.save(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PatchMapping("/{id}")
    public Booking updateBooking(@Valid @RequestBody Booking booking) {
        Booking existsBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found!"));
        if (!existsBooking.getAppointmentTime().equals(booking.getAppointmentTime()) && bookingRepository.existsAppointmentTime(booking.getAppointmentTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Time already reserved!");
        }
        return bookingRepository.save(booking);
    }

    @GetMapping("/reservations")
    public List<Booking> bookingList() {
        return bookingRepository.findAll();
    }
}
