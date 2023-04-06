package com.example.medappointmentscheduler.service.impl;

import com.example.medappointmentscheduler.domain.entity.Patient;
import com.example.medappointmentscheduler.domain.entity.User;
import com.example.medappointmentscheduler.domain.entity.enums.PatientGenderEnum;
import com.example.medappointmentscheduler.domain.model.SignupModel;
import com.example.medappointmentscheduler.error.PatientNotFoundException;
import com.example.medappointmentscheduler.repository.PatientRepository;
import com.example.medappointmentscheduler.repository.UserRepository;
import com.example.medappointmentscheduler.service.PatientService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Patient createPatient(SignupModel signupModel) {
        Patient patient = new Patient();
        patient.setFirstName(signupModel.getFirstName());
        patient.setLastName(signupModel.getLastName());
        patient.setEmail(signupModel.getEmail());
        patient.setPhone(signupModel.getPhone());
        patient.setDateOfBirth(java.sql.Date.valueOf(signupModel.getDateOfBirth()));
        patient.setGender(PatientGenderEnum.valueOf(signupModel.getGender()));
        patient.setMedicalHistory(signupModel.getMedicalHistory());
        patient.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        patient.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        User user = this.userRepository.findByEmail(signupModel.getEmail());

        patient.setUser(user);

        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long patientId) throws PatientNotFoundException {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    @Override
    public Patient updatePatient(Long patientId, Patient patientDetails) throws PatientNotFoundException {
        Patient patient = getPatientById(patientId);
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setMedicalHistory(patientDetails.getMedicalHistory());
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) throws PatientNotFoundException {
        Patient patient = getPatientById(patientId);
        patientRepository.delete(patient);
    }

}