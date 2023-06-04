package com.baouyen.doan.dto;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class GamePlayDto {
    private Long id;
    private GameDto game;
    private UserDto user;

    private Long playAt;

    private String playData;

    private Error error;

    public enum Error {
        VOUCHER_NOT_FOUND("Voucher was not found"),
        VOUCHER_EXPIRED("Voucher expired"),
        VOUCHER_HAS_USED("Voucher is already used");

        private String code;

        Error(String s) {
            this.code = s;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
