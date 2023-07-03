package com.example.medappointmentscheduler.service.impl;

import com.example.medappointmentscheduler.domain.entity.User;
import com.example.medappointmentscheduler.domain.entity.enums.UserRoleEnum;
import com.example.medappointmentscheduler.domain.model.SignupDoctorModel;
import com.example.medappointmentscheduler.domain.model.SignupModel;
import com.example.medappointmentscheduler.error.UserNotFoundException;
import com.example.medappointmentscheduler.repository.UserRepository;
import com.example.medappointmentscheduler.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminPass;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Value("admin") String adminPass) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminPass = adminPass;
    }

    public void initAdmin() {
        if (userRepository.count() == 0) {
            createUser("daniel.mihovv@gmail.com", adminPass, "admin");
        }
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(email);
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        String newPasswordHash = passwordEncoder.encode(newPassword);
        user.setPassword(newPasswordHash);

        userRepository.save(user);

        return true;
    }

    @Override
    public User createPatientUser(SignupModel signupModel) {
        return createUser(signupModel.getEmail(), signupModel.getPassword(), signupModel.getUserRole());
    }

    @Override
    public User createDoctorUser(SignupDoctorModel signupDoctorModel) {
        return createUser(signupDoctorModel.getEmail(), signupDoctorModel.getPassword(), signupDoctorModel.getUserRole());
    }

    private User createUser(String email, String password, String userRole) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive((byte) 1);
        user.setRole(UserRoleEnum.valueOf(userRole));
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
            user.setPassword(passwordEncoder.encode(signupModel.getPassword()));
            user.setEmail(signupModel.getEmail());
            user.setRole(UserRoleEnum.valueOf(signupModel.getUserRole()));
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Looking for user: " + username);

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
