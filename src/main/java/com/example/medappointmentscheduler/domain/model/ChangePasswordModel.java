package com.example.medappointmentscheduler.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangePasswordModel {
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Size(min = 6, max = 30)
    private String newPassword;
    @NotBlank
    @Size(min = 6, max = 30)
    private String confirmNewPassword;
}
