package com.example.medappointmentscheduler.error;

public class PatientEmailNotFoundException extends RuntimeException {
    public PatientEmailNotFoundException(String email) {
        super("Could not find patient with email: " + email);
    }
}
