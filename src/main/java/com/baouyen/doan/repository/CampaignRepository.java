package com.baouyen.doan.repository;

import com.baouyen.doan.dto.StoreDto;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.CampaignStatus;
import com.baouyen.doan.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    @Query("SELECT c FROM Campaign c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<Campaign> searchCampaigns(@Param("name") String name,
                                   Pageable pageable);

    @Query("SELECT c FROM Campaign c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND c.status = :status " +
            "AND (:storeType IS NULL OR c.store.type = :storeType)")
    Page<Campaign> searchCampaigns(@Param("name") String name,
                                   @Param("status") CampaignStatus status,
                                   @Param("storeType") StoreDto.StoreType storeType,
                                   Pageable pageable);

    Optional<Campaign> findById(Long campaignId);

    @Query("SELECT c FROM Campaign c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND c.partner = :partner")
    Page<Campaign> searchCampaigns(@Param("name") String name, @Param("partner") Partner partner,
                                   Pageable pageable);

    Page<Campaign> findByPartner(Partner partner, Pageable pageable);
    List<Campaign> findByPartner(Partner partner);

    @Query("SELECT c FROM Campaign c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND c.partner IN (:partners) " +
            "AND c.status = :status " +
            "AND (:storeType IS NULL OR c.store.type = :storeType)")
    Page<Campaign> searchCampaigns(@Param("name") String name,
                                   @Param("partners") List<Partner> partners,
                                   @Param("status") CampaignStatus status,
                                   @Param("storeType") StoreDto.StoreType storeType,
                                   Pageable pageable);

    @Query("SELECT c FROM Campaign c " +
            "WHERE c.partner IN (:partners) " +
            "AND (:storeType IS NULL OR c.store.type = :storeType)")
    Page<Campaign> findByPartnerIn(@Param("partners") List<Partner> partners,
                                   @Param("storeType") StoreDto.StoreType storeType,
                                   Pageable pageable);


    @Query("SELECT c FROM Campaign c " +
            "WHERE c.status = :status " +
            "AND (:storeType IS NULL OR c.store.type = :storeType)")
    Page<Campaign> findByStatus(@Param("status") CampaignStatus status,
                                @Param("storeType") StoreDto.StoreType storeType,
                                Pageable pageable);


}
