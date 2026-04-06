package com.agata.bookingapi.repository;

import com.agata.bookingapi.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByProfessionalNameAndAppointmentTime(String professionalName, LocalDateTime appointmentTime);
    List<Booking> findByProfessionalName(String professionalName);
    List<Booking> findByAppointmentTime(LocalDateTime appointmentTime);
}
