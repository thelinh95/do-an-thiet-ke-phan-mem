package com.baouyen.doan.dto;

public class SearchCampaignRequest {
    private String name;

    private Paginator paginator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
