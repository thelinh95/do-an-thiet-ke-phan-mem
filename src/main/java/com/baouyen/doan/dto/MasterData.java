package com.baouyen.doan.dto;

import java.util.List;

public class MasterData {
    List<GameType> gameTypes;
    List<VoucherDto.VOUCHER_TYPE> voucherTypes;

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
}
