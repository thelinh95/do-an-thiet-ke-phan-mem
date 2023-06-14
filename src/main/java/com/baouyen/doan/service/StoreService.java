package com.baouyen.doan.service;

import com.baouyen.doan.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreService {
    Page<StoreDto> searchPartnerStore(SearchStortRequest request);
    List<StoreDto> searchAllPartnerStore();
    void createPartnerStore(CreateStoreRequest request);
}
