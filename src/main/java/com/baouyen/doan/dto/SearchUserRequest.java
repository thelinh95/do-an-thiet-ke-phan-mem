package com.baouyen.doan.dto;

public class SearchUserRequest extends PaginationRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
