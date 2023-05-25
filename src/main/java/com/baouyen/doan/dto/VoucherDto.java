package com.baouyen.doan.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class VoucherDto {
    private Long id;
    private String description;
    private VOUCHER_TYPE type;

    private int quantity;

    private String gameRandomNumber;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public enum VOUCHER_TYPE {
        TEN_PERCENT_DIS_COUNT,
        TWEEN_TY_PERCENT_DIS_COUNT

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VOUCHER_TYPE getType() {
        return type;
    }

    public void setType(VOUCHER_TYPE type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGameRandomNumber() {
        return gameRandomNumber;
    }

    public void setGameRandomNumber(String gameRandomNumber) {
        this.gameRandomNumber = gameRandomNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
