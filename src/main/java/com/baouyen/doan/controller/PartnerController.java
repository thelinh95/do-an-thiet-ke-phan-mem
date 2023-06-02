package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.PartnerService;
import com.baouyen.doan.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private SecurityContextService securityContextService;

    @GetMapping(value = {"", "/profile", "/home"})
    public String home(Model model) {
        Partner currentPartner = securityContextService.getCurrentPartner();
        model.addAttribute("partner", currentPartner);
        return "partner/home";
    }

    @GetMapping(value = {"/campaign"})
    public String home() {
        return "partner/campaign";
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

    @PostMapping("/campaigns/{campaignId}/vouchers/create")
    @ResponseBody
    public Boolean createCampaignVoucher(@PathVariable Long campaignId, @RequestBody CreateCampaignVoucherRequest request) {
        return campaignService.createCampaignVoucher(campaignId, request);
    }

    @PostMapping("/{partnerId}/edit")
    @ResponseBody
    public Boolean editCampaign(@PathVariable Long partnerId, @RequestBody EditPartnerRequest request) {
        return campaignService.editPartner(partnerId, request);
    }

    @GetMapping("/campaigns/{campaignId}/vouchers/create")
    public String createVouchers(@PathVariable Long campaignId, Model model){
        model.addAttribute("campaignId", campaignId);
        return "partner/create-voucher";
    }

}

