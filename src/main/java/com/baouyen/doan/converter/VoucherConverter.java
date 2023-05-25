package com.baouyen.doan.converter;

import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.Voucher;
import org.springframework.stereotype.Component;

@Component
public class VoucherConverter {
    public Voucher dtoToEntity(VoucherDto voucherDto) {
        Voucher result = new Voucher();
        result.setId(voucherDto.getId());
        result.setDescription(voucherDto.getDescription());
        result.setCreatedAt(voucherDto.getCreatedAt());
        result.setQuantity(voucherDto.getQuantity());
        result.setType(voucherDto.getType());
        result.setGameRandomNumber(voucherDto.getGameRandomNumber());

        return result;
    }

    public VoucherDto entityToDto(Voucher voucher) {
        VoucherDto result = new VoucherDto();
        result.setId(voucher.getId());
        result.setDescription(voucher.getDescription());
        result.setCreatedAt(voucher.getCreatedAt());
        result.setQuantity(voucher.getQuantity());
        result.setType(voucher.getType());
        result.setGameRandomNumber(voucher.getGameRandomNumber());

        return result;

    }
}
