package com.baouyen.doan.dto;

public class SearchCampaignRequest extends PaginationRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
