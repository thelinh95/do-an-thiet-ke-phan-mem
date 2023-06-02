package com.baouyen.doan.controller;

import com.baouyen.doan.dto.Role;
import com.baouyen.doan.entity.User;
import com.baouyen.doan.service.PartnerService;
import com.baouyen.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, @ModelAttribute("user") User user) {
        Optional<User> byUsername = userService.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            request.setAttribute("error", "Username already existed");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);

        if(Role.PARTNER.name().equals(user.getRole())){
            partnerService.createPartner(user.getUsername());
        }

        return "redirect:/login";
    }
}

