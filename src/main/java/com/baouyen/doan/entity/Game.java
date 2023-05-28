package com.baouyen.doan.entity;

import com.baouyen.doan.dto.GameType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated
    private GameType gameType;

    @ManyToMany(mappedBy = "games")
    private List<Campaign> campaign;

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

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public List<Campaign> getCampaign() {
        return campaign;
    }

    public void setCampaign(List<Campaign> campaign) {
        this.campaign = campaign;
    }
}
