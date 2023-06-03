package com.baouyen.doan.dto;

public class UserSearchCampaignRequest extends PaginationRequest {
    private String name;

    private String partnerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}
