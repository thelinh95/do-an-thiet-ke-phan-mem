package com.baouyen.doan.controller;

import com.baouyen.doan.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @GetMapping(value = {"", "/home"})
    public String home() {
        return "partner/home";
    }


}

