package com.baouyen.doan.service;

import com.baouyen.doan.dto.*;
import org.springframework.data.domain.Page;

public interface CampaignService {
    Page<CampaignResponse> searchCampaign(SearchCampaignRequest request);
    Page<CampaignResponse> searchCampaign(UserSearchCampaignRequest request);
    Page<CampaignResponse> searchPartnerCampaign(SearchCampaignRequest request);
    void createPartnerCampaign(CreateCampaignRequest request);
    void deleteCampaign();

    boolean createCampaignVoucher(Long campaignId, CreateCampaignVoucherRequest request);

    Boolean editPartner(Long partnerId, EditPartnerRequest request);

    Page<VoucherDto> searchCampaignVoucher(Long campaignId, SearchCampaignVoucherRequest request);
}
