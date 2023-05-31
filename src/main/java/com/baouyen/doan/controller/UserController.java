package com.baouyen.doan.controller;

import com.baouyen.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/home"})
    public String home() {
        return "user/home";
    }

}

