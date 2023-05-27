package com.baouyen.doan.dto;

import java.util.Set;

public class CampaignResponse {
    private Long id;
    private String name;

    private String endDate;

    private String startDate;

    private PartnerDto partner;

    private Set<VoucherDto> vouchers;

    private Set<GameDto> games;

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

    public Set<VoucherDto> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Set<VoucherDto> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<GameDto> getGames() {
        return games;
    }

    public void setGames(Set<GameDto> games) {
        this.games = games;
    }
}
