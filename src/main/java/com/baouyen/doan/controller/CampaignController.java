package com.baouyen.doan.controller;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.dto.SearchCampaignRequest;
import com.baouyen.doan.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    /*
    @GetMapping
    public String searchCampaign(Model model) {
        List<CampaignDto> campaigns = campaignService.searchCampaign(request);
        model.addAttribute("campaigns", campaigns);
        return "admin/campaign";
    }
     */

    @PostMapping("/search")
    @ResponseBody
    public Page<CampaignResponse> searchCampaign(@RequestBody SearchCampaignRequest request) {
        return campaignService.searchCampaign(request);
    }

    @PostMapping()
    @ResponseBody
    public Boolean createCampaign(@RequestBody CreateCampaignRequest request) {
        campaignService.createCampaign(request);
        return true;
    }

    @GetMapping("/create")
    public String createCampaign() {
        return "campaign/create-campaign";
    }
}

