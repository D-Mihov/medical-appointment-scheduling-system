package com.example.medappointmentscheduler.domain.entity;

import com.example.medappointmentscheduler.domain.entity.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false, name = "last_login")
    private Timestamp lastLogin;

    public User() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public byte getIsActive() {
        return isActive;
    }

    public User setIsActive(byte isActive) {
        this.isActive = isActive;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public User setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public User setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }
}
