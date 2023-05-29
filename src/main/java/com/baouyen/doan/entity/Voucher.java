package com.baouyen.doan.entity;

import com.baouyen.doan.dto.VoucherDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private VoucherDto.VOUCHER_TYPE type;

    @Column
    private String gameRandomNumber;

    @Column
    private String gameRandomString;

    @Column
    private VoucherDto.VOUCHER_STATUS status;

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

    public String getGameRandomString() {
        return gameRandomString;
    }

    public void setGameRandomString(String gameRandomString) {
        this.gameRandomString = gameRandomString;
    }

    public VoucherDto.VOUCHER_STATUS getStatus() {
        return status;
    }

    public void setStatus(VoucherDto.VOUCHER_STATUS status) {
        this.status = status;
    }
}
