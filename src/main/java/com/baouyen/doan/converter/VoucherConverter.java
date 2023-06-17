package com.baouyen.doan.converter;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoucherConverter {
    @Autowired
    private StoreConverter storeConverter;

    public VoucherDto entityToDto(Voucher voucher) {
        VoucherDto result = new VoucherDto();
        result.setId(voucher.getId());
        result.setCode(voucher.getCode());
        result.setDescription(voucher.getDescription());
        result.setCreatedAt(voucher.getCreatedAt());
        result.setType(voucher.getType().getCode());
        result.setStatus(voucher.getStatus());
        result.setGameRandomNumber(voucher.getGameRandomNumber());
        result.setGameRandomString(voucher.getGameRandomString());
        result.setExpiredAt(voucher.getExpiredAt());

        CampaignResponse campaignResponse = new CampaignResponse();
        campaignResponse.setStore(storeConverter.entityToResponseDto(voucher.getCampaign().getStore()));
        result.setCampaign(campaignResponse);

        return result;

    }
}
