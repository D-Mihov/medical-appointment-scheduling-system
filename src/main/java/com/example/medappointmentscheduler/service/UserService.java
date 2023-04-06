package com.example.medappointmentscheduler.service;

import com.example.medappointmentscheduler.domain.entity.User;
import com.example.medappointmentscheduler.domain.model.SignupModel;

public interface UserService {
    User createUser(SignupModel signupModel);

    User getUserByEmail(String email);

    void deleteUser(String username);

    void updateUser(SignupModel signupModel);
}
