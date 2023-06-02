package com.baouyen.doan.entity;

import javax.persistence.*;

@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String streetAddress;
    @Column
    private String districtAddress;

    @Column
    private String wardAddress;

    @Column
    private String provinceAddress;

    @OneToOne
    private Campaign campaign;

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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getDistrictAddress() {
        return districtAddress;
    }

    public void setDistrictAddress(String districtAddress) {
        this.districtAddress = districtAddress;
    }

    public String getWardAddress() {
        return wardAddress;
    }

    public void setWardAddress(String wardAddress) {
        this.wardAddress = wardAddress;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
