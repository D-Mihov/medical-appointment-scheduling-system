package com.example.medappointmentscheduler.web;

import com.example.medappointmentscheduler.domain.model.SignupDoctorModel;
import com.example.medappointmentscheduler.service.DoctorService;
import com.example.medappointmentscheduler.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationDocController {

    private final UserService userService;

    private final DoctorService doctorService;

    public RegistrationDocController(UserService userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @GetMapping("/signupDoctor")
    public String prepareSignupDoc(Model model) {
        SignupDoctorModel signupDoctorModel = new SignupDoctorModel();

        model.addAttribute("signupDoctorModel", signupDoctorModel);

        return "signupDoc";
    }


    @PostMapping("/signupDoctor")
    public String signup(@ModelAttribute("signupDoctorModel") @Valid SignupDoctorModel signupDoctorModel, BindingResult bindingResult, Model model) {
        signupDoctorModel.setUserRole("DOCTOR");

        if (!signupDoctorModel.getPassword().equals(signupDoctorModel.getConfirmPassword())) {
            bindingResult.rejectValue("password", "signup.form.password.nomatch");
            bindingResult.rejectValue("confirmPassword", "signup.form.password.nomatch");
        }

        if (bindingResult.hasErrors()) {
            return "signupDoc";
        }

        this.userService.createDoctorUser(signupDoctorModel);
        this.doctorService.createDoctor(signupDoctorModel);

        return "redirect:/";
    }
}
