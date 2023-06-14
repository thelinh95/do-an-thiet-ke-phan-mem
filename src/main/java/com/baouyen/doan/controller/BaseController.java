package com.baouyen.doan.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    protected void addRemoteUserToModel(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getRemoteUser());
    }

}

