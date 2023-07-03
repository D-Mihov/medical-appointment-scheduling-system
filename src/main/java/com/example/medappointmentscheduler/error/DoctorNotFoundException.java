package com.example.medappointmentscheduler.error;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(Long id) {
        super("Could not find doctor with id: " + id);
    }
}
