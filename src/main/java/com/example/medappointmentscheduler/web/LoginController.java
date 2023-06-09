package com.example.medappointmentscheduler.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("bad_credentials",true);
        return "redirect:/login";
    }
}
