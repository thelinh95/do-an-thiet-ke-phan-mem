package com.baouyen.doan.entity;


import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    private Date playAt;

    @Column
    private String playData;

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
}
