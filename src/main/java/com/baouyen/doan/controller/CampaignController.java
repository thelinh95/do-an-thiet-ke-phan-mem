package com.baouyen.doan.controller;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.Paginator;
import com.baouyen.doan.dto.SearchCampaignRequest;
import com.baouyen.doan.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping()
    public Page<CampaignResponse> searchCampaign(@RequestBody SearchCampaignRequest request) {
        return campaignService.searchCampaign(request);
    }

}

