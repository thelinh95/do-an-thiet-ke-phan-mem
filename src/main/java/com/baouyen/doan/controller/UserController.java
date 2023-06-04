package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.UserService;
import com.baouyen.doan.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private VoucherService voucherService;

    @GetMapping(value = {"", "/home"})
    public String home() {
        return "user/home";
    }

    @PostMapping("/campaigns/search")
    @ResponseBody
    public Page<CampaignResponse> searchCampaign(@RequestBody UserSearchCampaignRequest request) {
        return campaignService.searchCampaign(request);
    }

    @GetMapping(value = {"/campaign"})
    public String campaign() {
        return "user/campaign";
    }

    @GetMapping("/campaigns/{campaignId}/vouchers")
    public String createVouchers(@PathVariable Long campaignId, Model model){
        model.addAttribute("campaignId", campaignId);
        return "user/voucher";
    }

    /**
     * Search campaign voucher to catch it. Only return the voucher which status is INITIAL
     * @param campaignId
     * @param request
     * @return
     */

    @PostMapping("/campaign/{campaignId}/vouchers/search")
    @ResponseBody
    public Page<VoucherDto> searchCampaignVoucher(@PathVariable Long campaignId,
                                                  @RequestBody SearchCampaignVoucherRequest request) {
        return campaignService.searchCampaignVoucher(campaignId, request);
    }

    @PostMapping("/vouchers/search")
    @ResponseBody
    public Page<VoucherDto> searchVoucher(@RequestBody SearchVoucherRequest request) {
        return voucherService.searchPartnerVoucher(request);
    }

}

