package com.baouyen.doan.service;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.SearchCampaignRequest;
import org.springframework.data.domain.Page;

public interface CampaignService {
    Page<CampaignResponse> searchCampaign(SearchCampaignRequest request);
    void createCampaign();
    void deleteCampaign();
}
