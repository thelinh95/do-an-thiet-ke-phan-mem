package com.baouyen.doan.controller;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.dto.CreateCampaignVoucherRequest;
import com.baouyen.doan.dto.SearchCampaignRequest;
import com.baouyen.doan.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/{campaignId}/vouchers/create")
    @ResponseBody
    public Boolean createCampaignVoucher(@PathVariable Long campaignId, @RequestBody CreateCampaignVoucherRequest request) {
        return campaignService.createCampaignVoucher(campaignId, request);
    }

    @GetMapping("/create")
    public String createCampaign() {
        return "campaign/create-campaign";
    }

    @GetMapping("/{campaignId}/vouchers/create")
    public String createVouchers(@PathVariable Long campaignId, Model model){
        model.addAttribute("campaignId", campaignId);
        return "campaign/create-voucher";
    }
}

