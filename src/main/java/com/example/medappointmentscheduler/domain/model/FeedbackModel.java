package com.example.medappointmentscheduler.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackModel {
    @NotBlank
    private String comment;
    @NotBlank
    private String rating;
    @NotNull
    private Long doctorId;
    @NotNull
    private Long patientId;
    @NotNull
    private Long appointmentId;
}
