package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.PartnerService;
import com.baouyen.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping(value = {"", "/home", "/campaign"})
    public String home() {
        return "admin/campaign";
    }

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    @PostMapping("/campaigns/search")
    @ResponseBody
    public Page<CampaignResponse> searchCampaign(@RequestBody SearchCampaignRequest request) {
        return campaignService.searchCampaign(request);
    }

    @GetMapping("/partner")
    public String partner() {
        return "admin/partner";
    }

    @PostMapping("/partners/search")
    @ResponseBody
    public Page<PartnerDto> searchPartner(@RequestBody SearchPartnerRequest request) {
        return partnerService.searchPartner(request);
    }

    @GetMapping("/game")
    public String game() {
        return "admin/game";
    }

    @GetMapping("/statistic")
    public String statistic() {
        return "admin/statistic";
    }

    @GetMapping("/user")
    public String user() {
        return "admin/user";
    }

    @PostMapping("/users/search")
    @ResponseBody
    public Page<UserDto> searchUser(@RequestBody SearchUserRequest request) {
        return userService.searchUser(request);
    }

}

