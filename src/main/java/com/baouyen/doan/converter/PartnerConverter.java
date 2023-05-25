package com.baouyen.doan.converter;

import com.baouyen.doan.dto.PartnerDto;
import com.baouyen.doan.entity.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerConverter {
    public Partner dtoToEntity(PartnerDto partnerDto) {
        Partner result = new Partner();
        result.setId(partnerDto.getId());
        result.setName(partnerDto.getName());
        result.setStreetAddress(partnerDto.getStreetAddress());
        result.setWardAddress(partnerDto.getWardAddress());
        result.setDistrictAddress(partnerDto.getDistrictAddress());
        result.setProvinceAddress(partnerDto.getProvinceAddress());

        return result;
    }


    public PartnerDto entityToDto(Partner partner) {
        PartnerDto result = new PartnerDto();
        result.setId(partner.getId());
        result.setName(partner.getName());
        result.setStreetAddress(partner.getStreetAddress());
        result.setWardAddress(partner.getWardAddress());
        result.setDistrictAddress(partner.getDistrictAddress());
        result.setProvinceAddress(partner.getProvinceAddress());

        return result;
    }
}
