package com.baouyen.doan.dto;

public enum GameType {
    LOTTERY_CHARACTER("Lottery character"),
    LOTTERY_NUMBER("Lottery number");

    private String code;

    GameType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
