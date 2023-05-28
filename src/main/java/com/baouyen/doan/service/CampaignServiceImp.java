package com.baouyen.doan.service;

import com.baouyen.doan.converter.CampaignConverter;
import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.dto.Paginator;
import com.baouyen.doan.dto.SearchCampaignRequest;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.CampaignStatus;
import com.baouyen.doan.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImp implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignConverter campaignConverter;

    @Override
    public Page<CampaignResponse> searchCampaign(SearchCampaignRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<Campaign> result;
        if (name != null) {
            result = campaignRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = campaignRepository.findAll(pageable);
        }

        return result.map(c -> campaignConverter.entityToResponseDto(c));

    }

    @Override
    public void createCampaign(CreateCampaignRequest request) {
        Campaign campaign = campaignConverter.requestDtoToEntity(request);
        campaign.setStatus(CampaignStatus.INITIAL);
        Campaign crreatedCampaign = campaignRepository.save(campaign);
    }

    @Override
    public void deleteCampaign() {

    }
}
