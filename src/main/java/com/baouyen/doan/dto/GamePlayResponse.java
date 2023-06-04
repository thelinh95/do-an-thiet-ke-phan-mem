package com.baouyen.doan.dto;

public class GamePlayResponse {
    private GameType gameType;
    private int numberDigit;

    public GamePlayResponse(GameType gameType, int gameRandomDigit) {
        this.gameType = gameType;
        this.numberDigit = gameRandomDigit;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getNumberDigit() {
        return numberDigit;
    }

    public void setNumberDigit(int numberDigit) {
        this.numberDigit = numberDigit;
    }
}
