package com.baouyen.doan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends BaseController {

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "redirect:/login";
    }

}

