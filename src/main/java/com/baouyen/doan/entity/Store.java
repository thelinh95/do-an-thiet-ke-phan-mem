package com.baouyen.doan.entity;

import com.baouyen.doan.dto.StoreDto;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String onlineAddress;

    @Column
    private String offlineAddress;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    @Enumerated
    private StoreDto.StoreType type;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Partner partner;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Campaign> campaigns;

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

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
    }

    public String getOfflineAddress() {
        return offlineAddress;
    }

    public void setOfflineAddress(String offlineAddress) {
        this.offlineAddress = offlineAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public StoreDto.StoreType getType() {
        return type;
    }

    public void setType(StoreDto.StoreType type) {
        this.type = type;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
