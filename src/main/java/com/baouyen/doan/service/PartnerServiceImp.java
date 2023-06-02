package com.baouyen.doan.service;

import com.baouyen.doan.converter.PartnerConverter;
import com.baouyen.doan.dto.Paginator;
import com.baouyen.doan.dto.PartnerDto;
import com.baouyen.doan.dto.SearchPartnerRequest;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImp implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerConverter partnerConverter;
    @Override
    public Page<PartnerDto> searchPartner(SearchPartnerRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<Partner> result;
        if (name != null) {
            result = partnerRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = partnerRepository.findAll(pageable);
        }

        return result.map(c -> partnerConverter.entityToDto(c));
    }

    @Override
    public void createPartner(String userName) {
        Partner p = new Partner();
        p.setName(userName);
        Partner save = partnerRepository.save(p);
    }

    @Override
    public void deletePartner() {

    }
}
