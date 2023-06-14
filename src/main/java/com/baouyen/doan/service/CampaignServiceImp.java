package com.baouyen.doan.service;

import com.baouyen.doan.converter.CampaignConverter;
import com.baouyen.doan.converter.VoucherConverter;
import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.*;
import com.baouyen.doan.repository.CampaignRepository;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.repository.StoreRepository;
import com.baouyen.doan.repository.VoucherRepository;
import com.baouyen.doan.util.DateTimeUtil;
import com.baouyen.doan.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignServiceImp implements CampaignService {

    public static final int GAME_RANDOM_DIGIT = 2;
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignConverter campaignConverter;

    @Autowired SecurityContextService securityContextService;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherConverter voucherConverter;
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Page<CampaignResponse> searchCampaign(SearchCampaignRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<Campaign> result;
        if (name != null) {
            result = campaignRepository.searchCampaigns(name, pageable);
        } else {
            result = campaignRepository.findAll(pageable);
        }

        return result.map(c -> campaignConverter.entityToResponseDto(c));
    }

    @Override
    public Page<CampaignResponse> searchCampaign(UserSearchCampaignRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        StoreDto.StoreType storeType = null;
        if(request.getStoreType() != null){
            if(!request.getStoreType().equals("All")){
                storeType = StoreDto.StoreType.valueOf(request.getStoreType());
            }
        }

        Page<Campaign> result;
        if (name != null) {
            if(request.getPartnerName() != null){
                List<Partner> partners = partnerRepository.findByNameContainingIgnoreCase(request.getPartnerName());

                result = campaignRepository.searchCampaigns(
                        name, partners, CampaignStatus.CREATED_VOUCHERS,
                        storeType, pageable);
            } else {
                result = campaignRepository.searchCampaigns(name,
                        CampaignStatus.CREATED_VOUCHERS,
                        storeType, pageable);
            }

        } else {
            if(request.getPartnerName() != null){
                List<Partner> partners = partnerRepository.findByNameContainingIgnoreCase(request.getPartnerName());
                result = campaignRepository.findByPartnerIn(partners,
                        storeType, pageable);
            } else {
                result = campaignRepository.findByStatus(CampaignStatus.CREATED_VOUCHERS,
                        storeType, pageable);
            }
        }

        return result.map(c -> campaignConverter.entityToResponseDto(c));
    }


    @Override
    public Page<CampaignResponse> searchPartnerCampaign(SearchCampaignRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Partner partner = securityContextService.getCurrentPartner();

        Page<Campaign> result;
        if (name != null) {
            result = campaignRepository.searchCampaigns(name, partner, pageable);
        } else {
            result = campaignRepository.findByPartner(partner, pageable);
        }

        return result.map(c -> campaignConverter.entityToResponseDto(c));
    }

    @Override
    public void createPartnerCampaign(CreateCampaignRequest request) {
        Optional<Store> storeOptional = storeRepository.findById(request.getStoreId());

        Partner currentPartner = securityContextService.getCurrentPartner();

        Campaign campaign = campaignConverter.requestDtoToEntity(request);
        campaign.setPartner(currentPartner);
        Set<Campaign> campaigns = currentPartner.getCampaigns();
        campaigns.add(campaign);
        campaign.setStatus(CampaignStatus.INITIAL);
        storeOptional.ifPresent(campaign::setStore);
        Campaign crreatedCampaign = campaignRepository.save(campaign);
    }

    @Override
    public void deleteCampaign() {

    }

    @Override
    public boolean createCampaignVoucher(Long campaignId, CreateCampaignVoucherRequest request) {
        Optional<Campaign> campaignOpt = campaignRepository.findById(campaignId);
        if (!campaignOpt.isPresent()) {
            return false;
        }

        Campaign campaign = campaignOpt.get();
        List<Voucher> tenPercentDiscountVouchers = createTenPercentDiscountVoucher(
                request, campaign);
        List<Voucher> twentyPercentDiscountVouchers = createTwentyPercentDiscountVoucher(
                request, campaign);

        Set<Voucher> vouchers = new HashSet<>(tenPercentDiscountVouchers);
        vouchers.addAll(twentyPercentDiscountVouchers);
        campaign.setVouchers(vouchers);
        campaign.setStatus(CampaignStatus.CREATED_VOUCHERS);
        Campaign createdCampaign = campaignRepository.save(campaign);
        return true;
    }



    @Override
    public Boolean editPartner(Long partnerId, EditPartnerRequest request) {
        Optional<Partner> partnerOpt = partnerRepository.findById(partnerId);
        if (!partnerOpt.isPresent()) {
            return false;
        }

        Partner partner = partnerOpt.get();
        partner.setStreetAddress(request.getStreetAddress());
        partner.setWardAddress(request.getWardAddress());
        partner.setDistrictAddress(request.getDistrictAddress());
        partner.setProvinceAddress(request.getProvinceAddress());
        Partner save = partnerRepository.save(partner);
        return true;
    }

    /**
     * Return only voucher which status is not USED
     * @param campaignId
     * @param request
     * @return
     */
    @Override
    public Page<VoucherDto> searchCampaignVoucher(Long campaignId, SearchCampaignVoucherRequest request) {
        String code = request.getCode();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        // TODO throw exception when campaign was not found or it's already expired.
        Optional<Campaign> campaignOpt = campaignRepository.findById(campaignId);

        Page<Voucher> result;

        if (code != null) {
            if(campaignOpt.isPresent()) {
                result = voucherRepository.searchVoucher(code, campaignOpt.get(),
                        VoucherDto.VOUCHER_STATUS.INITIAL, pageable);
            } else {
                result = voucherRepository.searchVoucher(code,
                        VoucherDto.VOUCHER_STATUS.INITIAL, pageable);
            }
        } else {
            if(campaignOpt.isPresent()) {
                result = voucherRepository.searchVoucher(campaignOpt.get(),
                        VoucherDto.VOUCHER_STATUS.INITIAL, pageable);
            } else {
                result = voucherRepository.findByStatus(
                        VoucherDto.VOUCHER_STATUS.INITIAL, pageable);
            }
        }

        return result.map(c -> voucherConverter.entityToDto(c));
    }

    private List<Voucher> createTwentyPercentDiscountVoucher(CreateCampaignVoucherRequest request, Campaign campaign) {
        List<Voucher> vouchers = new ArrayList<>();
        Integer twentyPercentDiscountQuantity = request.getTwentyPercentDiscountQuantity();
        if(twentyPercentDiscountQuantity != null && twentyPercentDiscountQuantity >0){
            for(int i = 0; i < twentyPercentDiscountQuantity; i++){
                Voucher voucher = createVoucher(request,
                        VoucherDto.VOUCHER_TYPE.TWENTY_PERCENT_DIS_COUNT, campaign);
                vouchers.add(voucher);
            }
        }
        return vouchers;
    }

    private static Voucher createVoucher(CreateCampaignVoucherRequest request,
                                         VoucherDto.VOUCHER_TYPE disCountType, Campaign campaign) {
        Voucher voucher = new Voucher();
        voucher.setDescription(request.getDescription());
        voucher.setType(disCountType);
        voucher.setCode(UUID.randomUUID().toString());
        voucher.setGameRandomNumber(String.valueOf(RandomUtil.generateRandomNumber(GAME_RANDOM_DIGIT))
                .toUpperCase());
        voucher.setGameRandomString(RandomUtil.generateRandomString(GAME_RANDOM_DIGIT));
        voucher.setStatus(VoucherDto.VOUCHER_STATUS.INITIAL);
        voucher.setCampaign(campaign);
        voucher.setExpiredAt(DateTimeUtil.getEndDateEpoch(campaign.getEndDate()));
        return voucher;
    }

    private List<Voucher> createTenPercentDiscountVoucher(CreateCampaignVoucherRequest request, Campaign campaign) {
        List<Voucher> vouchers = new ArrayList<>();
        Integer tenPercentDiscountQuantity = request.getTenPercentDiscountQuantity();
        if(tenPercentDiscountQuantity != null && tenPercentDiscountQuantity >0){
            for(int i = 0; i < tenPercentDiscountQuantity; i++){
                Voucher voucher = createVoucher(request,
                        VoucherDto.VOUCHER_TYPE.TEN_PERCENT_DIS_COUNT, campaign);
                vouchers.add(voucher);
            }
        }
        return vouchers;
    }


}
