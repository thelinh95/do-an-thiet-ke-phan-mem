package com.baouyen.doan.dto;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


public class GamePlayDto {
    private Long id;
    private GameDto game;
    private UserDto user;

    @Temporal(TemporalType.DATE)
    private Date playAt;

    private String playData;

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getPlayAt() {
        return playAt;
    }

    public void setPlayAt(Date playAt) {
        this.playAt = playAt;
    }

    public String getPlayData() {
        return playData;
    }

    public void setPlayData(String playData) {
        this.playData = playData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
