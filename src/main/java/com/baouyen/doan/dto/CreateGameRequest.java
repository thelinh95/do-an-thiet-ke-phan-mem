package com.baouyen.doan.dto;

import javax.validation.constraints.NotNull;

public class CreateGameRequest {
    @NotNull
    private String name;

    @NotNull
    private String gameType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
