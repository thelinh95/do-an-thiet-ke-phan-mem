package com.baouyen.doan.dto;

public class Paginator {

    public Paginator() {
    }

    private int page;
    private int size;

    public Paginator(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}