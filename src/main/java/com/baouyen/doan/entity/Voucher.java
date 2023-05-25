package com.baouyen.doan.entity;

import com.baouyen.doan.dto.VoucherDto;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String description;

    @Column
    private VoucherDto.VOUCHER_TYPE type;

    // game result will be announced on this day.

    @Column
    private int quantity;

    @Column
    private String gameRandomNumber;

    @Column
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VoucherDto.VOUCHER_TYPE getType() {
        return type;
    }

    public void setType(VoucherDto.VOUCHER_TYPE type) {
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
