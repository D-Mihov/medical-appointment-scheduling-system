package com.example.medappointmentscheduler.web;

import com.example.medappointmentscheduler.domain.entity.Appointment;
import com.example.medappointmentscheduler.domain.entity.Doctor;
import com.example.medappointmentscheduler.domain.model.AddAppointmentModel;
import com.example.medappointmentscheduler.domain.model.SignupDoctorModel;
import com.example.medappointmentscheduler.service.AppointmentService;
import com.example.medappointmentscheduler.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class AppointmentsController {
    private final AppointmentService appointmentService;

    @Autowired
    private Utils utils;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public String showAppointments(Model model, Principal principal) {
        String loggedInEmail = principal.getName();

        List<Appointment> appointments;

        if (utils.hasRole("ROLE_ADMIN")) {
            appointments = appointmentService.getAllAppointments();
        } else if (utils.hasRole("ROLE_DOCTOR")) {
            appointments = appointmentService.getAppointmentsByDoctor(loggedInEmail);
        } else if (utils.hasRole("ROLE_PATIENT")) {
            appointments = appointmentService.getAppointmentsByPatient(loggedInEmail);
        } else {
            appointments = Collections.emptyList();
        }

        model.addAttribute("appointments", appointments);

        return "appointments";
    }

    @GetMapping("/appointments/complete")
    public String completeAppointment(@RequestParam("id") Long id) {
        appointmentService.completeAppointment(id);

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/cancel")
    public String cancelAppointment(@RequestParam("id") Long id) {
        appointmentService.cancelAppointment(id);

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete")
    public String deleteAppointment(@RequestParam("id") Long id) {
        appointmentService.deleteAppointment(id);

        return "redirect:/appointments";
    }
}
