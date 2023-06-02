package com.baouyen.doan.service;

import com.baouyen.doan.converter.VoucherConverter;
import com.baouyen.doan.dto.Paginator;
import com.baouyen.doan.dto.SearchVoucherRequest;
import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.entity.Voucher;
import com.baouyen.doan.repository.CampaignRepository;
import com.baouyen.doan.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImp implements VoucherService {
    
    @Autowired
    private VoucherRepository voucherRepository;
    
    @Autowired
    private VoucherConverter voucherConverter;
    
    @Autowired
    private SecurityContextService securityContextService;

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public Page<VoucherDto> searchVoucher(SearchVoucherRequest request) {
        String name = request.getCode();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<Voucher> result;
        if (name != null) {
            result = voucherRepository.findByCodeContainingIgnoreCase(name, pageable);
        } else {
            result = voucherRepository.findAll(pageable);
        }

        return result.map(c -> voucherConverter.entityToDto(c));
    }

    @Override
    public Page<VoucherDto> searchPartnerVoucher(SearchVoucherRequest request) {
        String code = request.getCode();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Partner partner = securityContextService.getCurrentPartner();
        List<Campaign> campaigns = campaignRepository.findByPartner(partner);

        Page<Voucher> result;
        if (code != null) {
            result = voucherRepository.findByCodeContainingIgnoreCaseAndCampaignIn(code, campaigns, pageable);
        } else {
            result = voucherRepository.findByCampaignIn(campaigns, pageable);
        }

        return result.map(c -> voucherConverter.entityToDto(c));
    }
}
