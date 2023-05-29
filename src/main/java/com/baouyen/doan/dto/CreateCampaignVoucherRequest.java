package com.baouyen.doan.dto;

import javax.validation.constraints.NotNull;

public class CreateCampaignVoucherRequest {
    @NotNull
    private String description;

    private Integer tenPercentDiscountQuantity;
    private Integer twentyPercentDiscountQuantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTenPercentDiscountQuantity() {
        return tenPercentDiscountQuantity;
    }

    public void setTenPercentDiscountQuantity(Integer tenPercentDiscountQuantity) {
        this.tenPercentDiscountQuantity = tenPercentDiscountQuantity;
    }

    public Integer getTwentyPercentDiscountQuantity() {
        return twentyPercentDiscountQuantity;
    }

    public void setTwentyPercentDiscountQuantity(Integer twentyPercentDiscountQuantity) {
        this.twentyPercentDiscountQuantity = twentyPercentDiscountQuantity;
    }
}
