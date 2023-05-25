package com.baouyen.doan.dto;

import java.util.List;

public class CampaignResponse {
    private Long id;
    private String name;

    private String endDate;

    private String startDate;

    private PartnerDto partner;

    private List<VoucherDto> vouchers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerDto getPartner() {
        return partner;
    }

    public void setPartner(PartnerDto partner) {
        this.partner = partner;
    }

    public List<VoucherDto> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<VoucherDto> vouchers) {
        this.vouchers = vouchers;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
