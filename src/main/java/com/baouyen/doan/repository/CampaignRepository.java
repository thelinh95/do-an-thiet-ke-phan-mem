package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ashish on 13/5/17.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    Page<Campaign> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
