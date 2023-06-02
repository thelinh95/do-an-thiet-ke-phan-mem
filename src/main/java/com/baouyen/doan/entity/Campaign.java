package com.baouyen.doan.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @OneToOne
    private Partner partner;

    @Column
    @Enumerated
    private CampaignStatus status;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Voucher> vouchers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "have_game",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> games;

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

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }
}
