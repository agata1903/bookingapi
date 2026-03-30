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

    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingDTO request) {
        Booking booking = new Booking();
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
