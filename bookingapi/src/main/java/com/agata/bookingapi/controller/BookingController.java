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

        if (user.isBlocked()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is blocked and cannot create bookings");
        }

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
