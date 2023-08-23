package com.example.medappointmentscheduler.domain.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddAppointmentModel {
    @NotBlank
    private String patientFullName;
    @NotBlank
    @Pattern(regexp ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{jakarta.validation.constraints.Email.message}")
    private String patientEmail;
    @NotNull
    private Long doctorId;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate appointmentDate;
    @NotNull(message = "Time is required")
//    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format")
    private LocalTime appointmentHour;
    @NotBlank
    private String diseases;
    private String notes;
    private String status;
}
