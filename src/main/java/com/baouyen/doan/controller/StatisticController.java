package com.baouyen.doan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/admin/statistic")
public class StatisticController {

    @Autowired
//    private CampaignService campaignService;

    @GetMapping
    public String searchStatistic(Model model) {
//        List<Campaign> campaigns = campaignService.getAllCampaigns();
//        model.addAttribute("campaigns", campaigns);
        return "admin/statistic";
    }

}

