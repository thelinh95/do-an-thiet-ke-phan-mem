package com.baouyen.doan.dto;

import com.baouyen.doan.controller.MasterDataController;

import java.util.List;

public class MasterData {
    List<MasterDataController.MasterDataItem> gameTypes;
    List<MasterDataController.MasterDataItem> voucherTypes;
    List<GameDto> allGames;
    List<MasterDataController.MasterDataItem> storeTypes;

    public List<MasterDataController.MasterDataItem> getStoreTypes() {
        return storeTypes;
    }

    public List<MasterDataController.MasterDataItem> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<MasterDataController.MasterDataItem> gameTypes) {
        this.gameTypes = gameTypes;
    }

    public List<MasterDataController.MasterDataItem> getVoucherTypes() {
        return voucherTypes;
    }

    public void setVoucherTypes(List<MasterDataController.MasterDataItem> voucherTypes) {
        this.voucherTypes = voucherTypes;
    }

    public List<GameDto> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<GameDto> allGames) {
        this.allGames = allGames;
    }

    public void setStoreTypes(List<MasterDataController.MasterDataItem> storeTypes) {
        this.storeTypes = storeTypes;
    }
}
