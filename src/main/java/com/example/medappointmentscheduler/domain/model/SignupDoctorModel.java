package com.example.medappointmentscheduler.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupDoctorModel {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{javax.validation.constraints.Email.message}")
    private String email;
    @Size(min = 6, max = 30)
    private String password;
    @Size(min = 6, max = 30)
    private String confirmPassword;
    @Pattern(regexp="(^$|[0-9]{10})", message = "{signup.form.phone.invalid}")
    private String phone;
    @NotBlank
    private String speciality;
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "{signup.doctor.form.experience.invalid}")
    private String experience;
    private String education;
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}\\d{4}$", message = "{signup.form.doctorID.invalid}")
    private String doctorId;
    private String userRole;
}
