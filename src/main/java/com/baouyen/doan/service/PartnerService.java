package com.baouyen.doan.service;

import com.baouyen.doan.dto.PartnerDto;
import com.baouyen.doan.dto.SearchPartnerRequest;
import org.springframework.data.domain.Page;

public interface PartnerService {
    Page<PartnerDto> searchPartner(SearchPartnerRequest request);
    void createPartner();
    void deletePartner();
}
