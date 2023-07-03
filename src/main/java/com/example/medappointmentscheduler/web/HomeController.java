package com.example.medappointmentscheduler.web;

import com.example.medappointmentscheduler.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private Utils utils;

    @GetMapping("/")
    public String home(Model model) {

        if (utils.isLoggedIn()) {

//            if (utils.hasRole("ADMIN")) {
            return "admin";
//            }
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
