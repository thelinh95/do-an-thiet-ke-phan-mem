package com.baouyen.doan.controller;

import com.baouyen.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.authenticate(username, password)) {
//            return "redirect:/home";
            return "redirect:" + request.getSession().getAttribute("url_prior_login");
        } else {
            request.setAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

}