package com.baouyen.doan.dto;

import java.util.List;

public class MasterData {
    List<GameType> gameTypes;
    List<VoucherDto.VOUCHER_TYPE> voucherTypes;
    List<GameDto> allGames;

    public List<GameType> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<GameType> gameTypes) {
        this.gameTypes = gameTypes;
    }

    public List<VoucherDto.VOUCHER_TYPE> getVoucherTypes() {
        return voucherTypes;
    }

    public void setVoucherTypes(List<VoucherDto.VOUCHER_TYPE> voucherTypes) {
        this.voucherTypes = voucherTypes;
    }

    public List<GameDto> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<GameDto> allGames) {
        this.allGames = allGames;
    }
}
