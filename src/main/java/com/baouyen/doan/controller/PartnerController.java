package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/partners")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping()
    public Page<PartnerDto> searchCampaign(@RequestBody SearchPartnerRequest request) {
        String name = request.getName();
        Paginator paginator = request.getPaginator();

        return partnerService.searchPartner(request);
    }

}

