package com.baouyen.doan.controller;

import com.baouyen.doan.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
//@RequestMapping("/admin/partner")
public class PartnerController {

    @Autowired
    private CampaignService campaignService;

    /*
    @GetMapping
    public String searchPartner(Model model) {
        List<CampaignDto> campaigns = campaignService.searchCampaign(request);
        model.addAttribute("campaigns", campaigns);
        return "admin/partner";
    }
     */

}

