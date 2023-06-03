package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>{
    Page<Voucher> findByCodeContainingIgnoreCase(String name, Pageable pageable);
    Page<Voucher> findByCodeContainingIgnoreCaseAndCampaignIn(String name, List<Campaign> campaigns, Pageable pageable);
    Page<Voucher> findByCodeContainingIgnoreCaseAndCampaign(String name, Campaign campaign, Pageable pageable);
    Page<Voucher> findByCampaignIn(List<Campaign> campaigns, Pageable pageable);

    Page<Voucher> findByCampaign(Campaign campaign, Pageable pageable);

}
