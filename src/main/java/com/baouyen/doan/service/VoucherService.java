package com.baouyen.doan.service;

import com.baouyen.doan.dto.SearchVoucherRequest;
import com.baouyen.doan.dto.VoucherDto;
import org.springframework.data.domain.Page;

public interface VoucherService {
    Page<VoucherDto> searchVoucher(SearchVoucherRequest request);
    Page<VoucherDto> searchPartnerVoucher(SearchVoucherRequest request);

}
