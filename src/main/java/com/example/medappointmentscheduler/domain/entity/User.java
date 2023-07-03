package com.example.medappointmentscheduler.domain.entity;

import com.example.medappointmentscheduler.domain.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false, name = "last_login")
    private Timestamp lastLogin;

    public User() {
    }

    public User setPassword(String password) {
        this.password = password;
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

    // --------------------------------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Role: " + role.toString());
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive == 1;
    }

    @Override
    public boolean isEnabled() {
        return isActive == 1;
    }
}
