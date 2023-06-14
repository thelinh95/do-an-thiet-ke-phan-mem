package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
    @Query("SELECT c FROM Store c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<Store> searchStores(@Param("name") String name,
                                   Pageable pageable);

    Optional<Store> findById(Long StoreId);

    @Query("SELECT c FROM Store c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND c.partner = :partner")
    Page<Store> searchStores(@Param("name") String name, @Param("partner") Partner partner,
                                   Pageable pageable);

    Page<Store> findByPartner(Partner partner, Pageable pageable);
    List<Store> findByPartner(Partner partner);

    Page<Store> findByPartnerIn(List<Partner> partners, Pageable pageable);

}
