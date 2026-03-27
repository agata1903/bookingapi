package com.agata.bookingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/schedule")
    public Booking scheduleTime(@Valid Booking booking) {
        if (bookingRepository.existsAppointmentTime(booking.getAppointmentTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Time already reserved!");
        }
        return bookingRepository.save(booking);
    }

    @GetMapping("/reservations")
    public List<Booking> bookingList() {
        return bookingRepository.findAll();
    }
}
