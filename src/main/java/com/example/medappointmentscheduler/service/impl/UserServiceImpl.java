package com.example.medappointmentscheduler.service.impl;

import com.example.medappointmentscheduler.domain.entity.User;
import com.example.medappointmentscheduler.domain.entity.enums.UserRoleEnum;
import com.example.medappointmentscheduler.domain.model.SignupModel;
import com.example.medappointmentscheduler.repository.UserRepository;
import com.example.medappointmentscheduler.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(SignupModel signupModel) {
        User user = new User();
        user.setEmail(signupModel.getEmail());
        user.setPasswordHash(passwordEncoder.encode(signupModel.getPassword()));
        user.setIsActive((byte) 1);
        user.setRole(UserRoleEnum.valueOf(signupModel.getUserRole()));
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public void updateUser(SignupModel signupModel) {
        User user = userRepository.findByEmail(signupModel.getEmail());
        if (user != null) {
            user.setPasswordHash(signupModel.getPassword());
            user.setEmail(signupModel.getEmail());
            user.setRole(UserRoleEnum.valueOf(signupModel.getUserRole()));
            userRepository.save(user);
        }
    }
}
