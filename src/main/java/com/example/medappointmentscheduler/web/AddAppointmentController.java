package com.example.medappointmentscheduler.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddAppointmentController {

    @GetMapping("/add-appointment")
    public String prepareAddAppointment(Model model) {

        return "add-appointment";
    }
}
