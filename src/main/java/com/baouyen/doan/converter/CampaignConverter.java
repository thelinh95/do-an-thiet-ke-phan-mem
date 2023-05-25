package com.baouyen.doan.converter;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CampaignConverter {
    @Autowired
    private PartnerConverter partnerConverter;

    @Autowired
    private VoucherConverter voucherConverter;

    public Campaign dtoToEntity(CampaignResponse campaignResponse) {
        Campaign result = new Campaign();
        result.setId(campaignResponse.getId());
        result.setName(campaignResponse.getName());
        result.setPartner(partnerConverter.dtoToEntity(campaignResponse.getPartner()));
        result.setVouchers(campaignResponse.getVouchers().stream()
                .map(v -> voucherConverter.dtoToEntity(v)).collect(Collectors.toList()));
        return result;
    }


    public CampaignResponse entityToResponseDto(Campaign campaign) {
        CampaignResponse result = new CampaignResponse();
        result.setId(campaign.getId());
        result.setName(campaign.getName());
        result.setStartDate(DateTimeUtil.localDateToString(campaign.getStartDate(), "dd/MM/yyyy"));
        result.setEndDate(DateTimeUtil.localDateToString(campaign.getEndDate(), "dd/MM/yyyy"));
        result.setPartner(partnerConverter.entityToDto(campaign.getPartner()));
        result.setVouchers(campaign.getVouchers().stream()
                .map(v -> voucherConverter.entityToDto(v)).collect(Collectors.toList()));
        return result;
    }
}
