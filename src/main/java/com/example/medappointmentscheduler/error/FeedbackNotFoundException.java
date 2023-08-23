package com.example.medappointmentscheduler.error;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(Long feedbackId) {
        super("Could not find feedback with id: " + feedbackId);
    }
}
