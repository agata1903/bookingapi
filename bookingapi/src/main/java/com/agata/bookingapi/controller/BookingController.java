package com.agata.bookingapi.controller;

import com.agata.bookingapi.dto.BookingDTO;
import com.agata.bookingapi.model.Booking;
import com.agata.bookingapi.model.User;
import com.agata.bookingapi.model.enums.BookingStatus;
import com.agata.bookingapi.repository.AuthRepository;
import com.agata.bookingapi.repository.BookingRepository;
import com.agata.bookingapi.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingDTO request) {
        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PatchMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDTO request) {
        return bookingService.updateBooking(id, request);
    }
}
