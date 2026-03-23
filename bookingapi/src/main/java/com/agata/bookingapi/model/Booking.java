package com.agata.bookingapi.model;

import com.agata.bookingapi.model.enums.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String clientName;
    private String clientPhone;
    private String professionalName;
    private String service;
    private LocalDateTime appointmentTime;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking() {
    }

    public Booking(long id, User user, String clientName, String clientPhone, String professionalName, String service, LocalDateTime appointmentTime, BookingStatus status) {
        this.id = id;
        this.user = user;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.professionalName = professionalName;
        this.service = service;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Booking(User user, String clientName, String clientPhone, String professionalName, String service, LocalDateTime appointmentTime, BookingStatus status) {
        this.user = user;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.professionalName = professionalName;
        this.service = service;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
