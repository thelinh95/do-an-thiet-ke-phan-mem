package com.baouyen.doan.controller;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.dto.SearchCampaignRequest;
import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CampaignService campaignService;

    @GetMapping(value = {"", "/home", "/campaign"})
    public String home() {
        return "partner/home";
    }

    @PostMapping("/campaigns/search")
    @ResponseBody
    public Page<CampaignResponse> searchCampaign(@RequestBody SearchCampaignRequest request) {
        return campaignService.searchPartnerCampaign(request);
    }

    @PostMapping("/campaigns")
    @ResponseBody
    public Boolean createCampaign(@RequestBody CreateCampaignRequest request) {
        campaignService.createPartnerCampaign(request);
        return true;
    }

    @GetMapping("/campaign/create")
    public String createCampaign() {
        return "partner/create-campaign";
    }

}

