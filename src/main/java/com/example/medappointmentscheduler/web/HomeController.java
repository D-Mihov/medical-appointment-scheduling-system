package com.example.medappointmentscheduler.web;

import com.example.medappointmentscheduler.domain.entity.Patient;
import com.example.medappointmentscheduler.service.AppointmentService;
import com.example.medappointmentscheduler.service.DoctorService;
import com.example.medappointmentscheduler.service.PatientService;
import com.example.medappointmentscheduler.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    private Utils utils;

    public HomeController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (utils.isLoggedIn()) {
            String loggedInEmail = principal.getName();

            if (utils.hasRole("ROLE_DOCTOR")) {
                int appointmentCount = appointmentService.getAppointmentCountForDoctor(loggedInEmail);
                int scheduledAppointmentCount = appointmentService.getScheduledAppointmentCountForDoctor(loggedInEmail);
                int completedAppointmentCount = appointmentService.getCompletedAppointmentCountForDoctor(loggedInEmail);
                int canceledAppointmentCount = appointmentService.getCanceledAppointmentCountForDoctor(loggedInEmail);
                int feedbacksCount = appointmentService.getFeedbacksCountForDoctor(loggedInEmail);
                String doctorFullName = doctorService.getDoctorByEmail(loggedInEmail).getFullName();

                model.addAttribute("appointmentCount", appointmentCount);
                model.addAttribute("scheduledAppointmentCount", scheduledAppointmentCount);
                model.addAttribute("doctorFullName", doctorFullName);
                model.addAttribute("completedAppointmentCount", completedAppointmentCount);
                model.addAttribute("canceledAppointmentCount", canceledAppointmentCount);
                model.addAttribute("feedbacksCount", feedbacksCount);
            }

            if (utils.hasRole("ROLE_PATIENT")) {
                Patient patient = patientService.getPatientByEmail(loggedInEmail);
                String patientFullName = patient.getFullName();
                int appointmentCount = appointmentService.getAppointmentCountForPatient(loggedInEmail);
                int scheduledAppointmentCount = appointmentService.getScheduledAppointmentCountForPatient(loggedInEmail);
                int completedAppointmentCount = appointmentService.getCompletedAppointmentCountForPatient(loggedInEmail);
                int canceledAppointmentCount = appointmentService.getCanceledAppointmentCountForPatient(loggedInEmail);
                int feedbacksCount = appointmentService.getFeedbacksCountForPatient(loggedInEmail);

                model.addAttribute("patientFullName", patientFullName);
                model.addAttribute("appointmentCount", appointmentCount);
                model.addAttribute("scheduledAppointmentCount", scheduledAppointmentCount);
                model.addAttribute("completedAppointmentCount", completedAppointmentCount);
                model.addAttribute("canceledAppointmentCount", canceledAppointmentCount);
                model.addAttribute("feedbacksCount", feedbacksCount);
            }

            if (utils.hasRole("ROLE_ADMIN")) {
                int patientsCount = patientService.getAllPatients().size();
                int doctorsCount = doctorService.getAllDoctors().size();
                int appointmentCount = appointmentService.getAllAppointments().size();
                int scheduledAppointmentCount = appointmentService.getScheduledAppointmentCount();
                int completedAppointmentCount = appointmentService.getCompletedAppointmentCount();
                int canceledAppointmentCount = appointmentService.getCanceledAppointmentCount();
                int feedbacksCount = appointmentService.getFeedbacksCount();

                model.addAttribute("patientsCount", patientsCount);
                model.addAttribute("doctorsCount", doctorsCount);
                model.addAttribute("appointmentCount", appointmentCount);
                model.addAttribute("scheduledAppointmentCount", scheduledAppointmentCount);
                model.addAttribute("completedAppointmentCount", completedAppointmentCount);
                model.addAttribute("canceledAppointmentCount", canceledAppointmentCount);
                model.addAttribute("feedbacksCount", feedbacksCount);
            }
            return "admin";
        }
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        log.debug(String.valueOf(isAdmin));
        return "admin";
    }
}
