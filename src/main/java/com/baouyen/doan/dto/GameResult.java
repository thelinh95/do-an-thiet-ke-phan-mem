package com.baouyen.doan.dto;

public class GameResult {
    private boolean isWin;
    private String failedErrorMessage;

    // contain voucher code and note used before time.
    // TODO can add store location: real address or online.
    private String winMessage;

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public String getFailedErrorMessage() {
        return failedErrorMessage;
    }

    public void setFailedErrorMessage(String failedErrorMessage) {
        this.failedErrorMessage = failedErrorMessage;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public void setWinMessage(String winMessage) {
        this.winMessage = winMessage;
    }
}
