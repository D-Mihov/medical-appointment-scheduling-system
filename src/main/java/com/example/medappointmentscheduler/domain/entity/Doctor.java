package com.example.medappointmentscheduler.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity{
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String speciality;

    private int experience;

    @Column(nullable = false)
    private String education;

    @OneToOne
    private User user;

    public Doctor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Doctor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Doctor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Doctor setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Doctor setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Doctor setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Doctor setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public Doctor setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public Doctor setEducation(String education) {
        this.education = education;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Doctor setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", speciality='" + speciality + '\'' +
                ", experience=" + experience +
                ", education='" + education + '\'' +
                ", user=" + user +
                '}';
    }
}
