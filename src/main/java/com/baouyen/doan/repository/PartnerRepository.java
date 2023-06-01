package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>{
    Page<Partner> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Partner> findByNameContainingIgnoreCase(String name);

}
