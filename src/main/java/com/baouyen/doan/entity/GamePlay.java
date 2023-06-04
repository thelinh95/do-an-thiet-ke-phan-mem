package com.baouyen.doan.entity;


import com.baouyen.doan.dto.GamePlayDto;

import javax.persistence.*;

@Entity
public class GamePlay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private User user;

    private Long playAt;

    @Column
    private String playData;

    @Column
    @Enumerated
    private GamePlayDto.Error error;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPlayAt() {
        return playAt;
    }

    public void setPlayAt(Long playAt) {
        this.playAt = playAt;
    }

    public String getPlayData() {
        return playData;
    }

    public void setPlayData(String playData) {
        this.playData = playData;
    }

    public GamePlayDto.Error getError() {
        return error;
    }

    public void setError(GamePlayDto.Error error) {
        this.error = error;
    }
}
