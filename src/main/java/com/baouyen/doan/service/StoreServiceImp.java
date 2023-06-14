package com.baouyen.doan.service;

import com.baouyen.doan.converter.StoreConverter;
import com.baouyen.doan.dto.CreateStoreRequest;
import com.baouyen.doan.dto.Paginator;
import com.baouyen.doan.dto.SearchStortRequest;
import com.baouyen.doan.dto.StoreDto;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.entity.Store;
import com.baouyen.doan.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StoreServiceImp implements StoreService{

    @Autowired
    private SecurityContextService securityContextService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreConverter storeConverter;

    @Override
    public Page<StoreDto> searchPartnerStore(SearchStortRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Partner partner = securityContextService.getCurrentPartner();

        Page<Store> result;
        if (name != null) {
            result = storeRepository.searchStores(name, partner, pageable);
        } else {
            result = storeRepository.findByPartner(partner, pageable);
        }

        return result.map(c -> storeConverter.entityToResponseDto(c));

    }

    @Override
    public List<StoreDto> searchAllPartnerStore() {
        Partner partner = securityContextService.getCurrentPartner();
        List<Store> stores = storeRepository.findByPartner(partner);
        return stores.stream().map(c -> storeConverter.entityToResponseDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public void createPartnerStore(CreateStoreRequest request) {
        Partner currentPartner = securityContextService.getCurrentPartner();
        Store store = storeConverter.requestDtoToEntity(request);
        store.setPartner(currentPartner);

        Set<Store> stores = currentPartner.getStores();
        stores.add(store);

        Store createdStore = storeRepository.save(store);
    }
}
