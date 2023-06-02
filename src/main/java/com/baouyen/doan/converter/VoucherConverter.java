package com.baouyen.doan.converter;

import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.Voucher;
import org.springframework.stereotype.Component;

@Component
public class VoucherConverter {

    public VoucherDto entityToDto(Voucher voucher) {
        VoucherDto result = new VoucherDto();
        result.setId(voucher.getId());
        result.setCode(voucher.getCode());
        result.setDescription(voucher.getDescription());
        result.setCreatedAt(voucher.getCreatedAt());
        result.setType(voucher.getType().getCode());
        result.setStatus(voucher.getStatus());
        result.setGameRandomNumber(voucher.getGameRandomNumber());

        return result;

    }
}
