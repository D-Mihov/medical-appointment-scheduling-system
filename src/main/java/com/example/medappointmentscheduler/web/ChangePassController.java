package com.example.medappointmentscheduler.web;

import com.example.medappointmentscheduler.domain.model.ChangePasswordModel;
import com.example.medappointmentscheduler.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ChangePassController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    public ChangePassController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changePassword")
    public String changePass(Model model) {
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();

        model.addAttribute("changePasswordModel", changePasswordModel);

        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePass(@Valid @ModelAttribute("changePasswordModel") ChangePasswordModel changePasswordModel,
                             BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "changePassword";
        }

        if (!changePasswordModel.getNewPassword().equals(changePasswordModel.getConfirmNewPassword())){
            bindingResult.rejectValue("newPassword", "form.password.nomatch");
            bindingResult.rejectValue("confirmNewPassword", "form.password.nomatch");
        }

        if (bindingResult.hasErrors()) {
            return "changePassword";
        }

        String email = principal.getName();
        boolean isPasswordChanged = userService.changePassword(email, changePasswordModel.getOldPassword(),
                changePasswordModel.getNewPassword());

        if (isPasswordChanged) {
            return "redirect:/admin";
        } else {
            bindingResult.rejectValue("oldPassword", "form.password.invalid");
            return "changePassword";
        }
    }
}
