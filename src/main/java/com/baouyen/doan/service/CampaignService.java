package com.baouyen.doan.service;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.dto.CreateCampaignVoucherRequest;
import com.baouyen.doan.dto.SearchCampaignRequest;
import org.springframework.data.domain.Page;

public interface CampaignService {
    Page<CampaignResponse> searchCampaign(SearchCampaignRequest request);
    Page<CampaignResponse> searchPartnerCampaign(SearchCampaignRequest request);
    void createPartnerCampaign(CreateCampaignRequest request);
    void deleteCampaign();

    boolean createCampaignVoucher(Long campaignId, CreateCampaignVoucherRequest request);

}
