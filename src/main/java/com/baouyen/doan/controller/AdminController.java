package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.PartnerService;
import com.baouyen.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private CampaignService campaignService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/home", "/campaign"})
    public String home(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "admin/campaign";
    }

    @PostMapping("/campaigns/search")
    @ResponseBody
    public Page<CampaignResponse> searchCampaign(@RequestBody SearchCampaignRequest request) {
        return campaignService.searchCampaign(request);
    }

    @GetMapping("/partner")
    public String partner(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "admin/partner";
    }

    @PostMapping("/partners/search")
    @ResponseBody
    public Page<PartnerDto> searchPartner(@RequestBody SearchPartnerRequest request) {
        return partnerService.searchPartner(request);
    }

    @GetMapping("/game")
    public String game(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "admin/game";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "admin/user";
    }

    @PostMapping("/users/search")
    @ResponseBody
    public Page<UserDto> searchUser(@RequestBody SearchUserRequest request) {
        return userService.searchUser(request);
    }

}

