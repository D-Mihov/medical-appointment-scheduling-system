package com.example.medappointmentscheduler.service;

import com.example.medappointmentscheduler.domain.entity.Patient;
import com.example.medappointmentscheduler.domain.model.SignupModel;

import java.util.List;

public interface PatientService {
    Patient createPatient(SignupModel signupModel);

    List<Patient> getAllPatients();

    Patient getPatientById(Long patientId);

    Patient updatePatient(Long patientId, Patient patientDetails);

    void deletePatient(Long patientId);
}
