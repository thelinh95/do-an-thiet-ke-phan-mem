package com.baouyen.doan.dto;

public class SearchVoucherRequest extends PaginationRequest {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
