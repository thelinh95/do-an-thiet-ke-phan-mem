package com.baouyen.doan.converter;

import com.baouyen.doan.dto.CreateStoreRequest;
import com.baouyen.doan.dto.StoreDto;
import com.baouyen.doan.entity.Store;
import com.baouyen.doan.repository.GameRepository;
import com.baouyen.doan.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
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

    public Store requestDtoToEntity(CreateStoreRequest request) {
        Store result = new Store();
        result.setName(request.getName());

        if(StoreDto.StoreType.ONLINE.name().equals(request.getType())){
            result.setType(StoreDto.StoreType.ONLINE);
            result.setOnlineAddress(request.getOnlineAddress());
        }else {
            result.setType(StoreDto.StoreType.OFFLINE);
            result.setOfflineAddress(request.getOfflineAddress());
            result.setLatitude(request.getLatitude());
            result.setLongitude(request.getLongitude());
        }

        return result;
    }

    public StoreDto entityToResponseDto(Store store) {
        StoreDto result = new StoreDto();
        result.setId(store.getId());
        result.setLatitude(store.getLatitude());
        result.setLongitude(store.getLongitude());
        result.setType(store.getType());
        result.setOfflineAddress(store.getOfflineAddress());
        result.setOnlineAddress(store.getOnlineAddress());
        result.setName(store.getName());

        return result;
    }
}
