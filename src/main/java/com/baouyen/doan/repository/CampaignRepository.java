package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    Page<Campaign> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Campaign> findById(Long campaignId);

    Page<Campaign> findByNameContainingIgnoreCaseAndPartner(String name, Partner partner, Pageable pageable);

    Page<Campaign> findByPartner(Partner partner, Pageable pageable);
}
