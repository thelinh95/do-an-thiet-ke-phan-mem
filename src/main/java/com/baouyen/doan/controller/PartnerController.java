package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("partner")
public class PartnerController extends BaseController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private VoucherService  voucherService;

    @Autowired
    private SecurityContextService securityContextService;

    @Autowired
    private StoreService storeService;

    @GetMapping(value = {"", "/profile", "/home"})
    public String home(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        Partner currentPartner = securityContextService.getCurrentPartner();
        model.addAttribute("partner", currentPartner);
        return "partner/home";
    }

    @GetMapping(value = {"/campaign"})
    public String campaign(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "partner/campaign";
    }

    @GetMapping(value = {"/voucher"})
    public String voucher(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "partner/voucher";
    }

    @GetMapping(value = {"/store"})
    public String store(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "partner/store";
    }

    @PostMapping("/stores/search")
    @ResponseBody
    public Page<StoreDto> searchStore(@RequestBody SearchStortRequest request) {
        return storeService.searchPartnerStore(request);
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

    @PostMapping("/stores")
    @ResponseBody
    public Boolean createStore(@RequestBody CreateStoreRequest request) {
        storeService.createPartnerStore(request);
        return true;
    }

    @PostMapping("/vouchers/search")
    @ResponseBody
    public Page<VoucherDto> searchVoucher(@RequestBody SearchVoucherRequest request) {
        return voucherService.searchPartnerVoucher(request);
    }

    @GetMapping("/campaign/create")
    public String createCampaign(HttpServletRequest request, Model model) {
        List<StoreDto> storeDtos = storeService.searchAllPartnerStore();
        model.addAttribute("stores", storeDtos);
        addRemoteUserToModel(model, request);
        return "partner/create-campaign";
    }

    @GetMapping("/store/create")
    public String createStore(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "partner/create-store";
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
    public String createVouchers(@PathVariable Long campaignId, Model model,
                                 HttpServletRequest request){
        addRemoteUserToModel(model, request);
        model.addAttribute("campaignId", campaignId);
        return "partner/create-voucher";
    }

}

