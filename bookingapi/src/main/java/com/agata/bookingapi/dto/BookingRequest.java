package com.agata.bookingapi.dto;

import java.time.LocalDateTime;

public class BookingRequest {

    private Long userId;
    private String clientName;
    private String clientPhone;
    private String professionalName;
    private String service;
    private LocalDateTime appointmentTime;

    public BookingRequest() {
    }

    public BookingRequest(Long userId, String clientName, String clientPhone, String professionalName, String service, LocalDateTime appointmentTime) {
        this.userId = userId;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.professionalName = professionalName;
        this.service = service;
        this.appointmentTime = appointmentTime;
    }

    public BookingRequest(String clientName, String clientPhone, String professionalName, String service, LocalDateTime appointmentTime) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.professionalName = professionalName;
        this.service = service;
        this.appointmentTime = appointmentTime;
    }

    public Long getUserId() {
        return userId;
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
}
