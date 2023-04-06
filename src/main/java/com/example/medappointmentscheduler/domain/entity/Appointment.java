package com.example.medappointmentscheduler.domain.entity;

import com.example.medappointmentscheduler.domain.entity.enums.AppointmentStatusEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity{
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Time time;

    private int duration;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    public Appointment() {
    }

    public Date getDate() {
        return date;
    }

    public Appointment setDate(Date date) {
        this.date = date;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public Appointment setTime(Time time) {
        this.time = time;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Appointment setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AppointmentStatusEnum getStatus() {
        return status;
    }

    public Appointment setStatus(AppointmentStatusEnum status) {
        this.status = status;
        return this;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Appointment setCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Appointment setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Appointment setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", status=" + status +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
