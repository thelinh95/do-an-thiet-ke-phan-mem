package com.baouyen.doan.converter;

import com.baouyen.doan.dto.CampaignResponse;
import com.baouyen.doan.dto.CreateCampaignRequest;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.Game;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.repository.GameRepository;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.baouyen.doan.util.RandomUtil.generateRandomCharacter;

@Component
public class CampaignConverter {
    @Autowired
    private PartnerConverter partnerConverter;

    @Autowired
    private VoucherConverter voucherConverter;

    @Autowired
    private GameConverter gameConverter;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private StoreConverter storeConverter;

    public Campaign requestDtoToEntity(CreateCampaignRequest request) {
        Campaign result = new Campaign();
        result.setName(request.getName());

        result.setStartDate(DateTimeUtil.stringToDate(
                request.getStartDate(), "dd/MM/yyyy"));

        result.setEndDate(DateTimeUtil.stringToDate(
                request.getEndDate(), "dd/MM/yyyy"));

        List<Game> gamesByIds = gameRepository.findByIdIn(request.getGameIds());
        result.setGames(new HashSet<>(gamesByIds));

        return result;
    }

    public CampaignResponse entityToResponseDto(Campaign campaign) {
        CampaignResponse result = new CampaignResponse();
        result.setId(campaign.getId());
        result.setName(campaign.getName());
        result.setStartDate(DateTimeUtil.localDateToString(campaign.getStartDate(), "dd/MM/yyyy"));
        result.setEndDate(DateTimeUtil.localDateToString(campaign.getEndDate(), "dd/MM/yyyy"));
        result.setPartner(partnerConverter.entityToDto(campaign.getPartner()));

        result.setVouchers(campaign.getVouchers().stream()
                .map(v -> voucherConverter.entityToDto(v)).collect(Collectors.toSet()));

        result.setGames(campaign.getGames().stream()
                .map(v -> gameConverter.entityToDto(v)).collect(Collectors.toSet()));
        result.setStatus(campaign.getStatus());
        result.setStore( storeConverter.entityToResponseDto(campaign.getStore()));
        return result;
    }
}
