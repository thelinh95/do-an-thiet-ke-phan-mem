package com.baouyen.doan.dto;

public class RedeemGameRequest {
    private String playData;

    private Long playedAt;
    private String gameType;

    public String getPlayData() {
        return playData;
    }

    public void setPlayData(String playData) {
        this.playData = playData;
    }

    public Long getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Long playedAt) {
        this.playedAt = playedAt;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
