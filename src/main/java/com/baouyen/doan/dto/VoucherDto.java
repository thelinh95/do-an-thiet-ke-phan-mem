package com.baouyen.doan.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class VoucherDto {
    private Long id;

    private String code;
    private String description;
    private VOUCHER_TYPE type;

    private String gameRandomNumber;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public enum VOUCHER_TYPE {
        TEN_PERCENT_DIS_COUNT ("ten percent discount"),
        TWENTY_PERCENT_DIS_COUNT("twenty percent discount");

        private String code;

        VOUCHER_TYPE(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public enum VOUCHER_STATUS {
        INITIAL,
        USED
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
