package com.baouyen.doan.repository;

import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.User;
import com.baouyen.doan.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>{

    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%', :code, '%'))")
    Page<Voucher> findByCodeContainingIgnoreCase(@Param("code") String code, Pageable pageable);

    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%', :code, '%')) " +
            "AND c.status = :status")
    Page<Voucher> searchVoucher(@Param("code") String code,
                                @Param("status") VoucherDto.VOUCHER_STATUS status, Pageable pageable);

    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%', :code, '%')) AND v.campaign IN :campaigns")
    Page<Voucher> findByCodeContainingIgnoreCaseAndCampaignIn(@Param("code") String code,
                                                              @Param("campaigns") List<Campaign> campaigns, Pageable pageable);

    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%', :code, '%')) AND v.campaign = :campaign " +
            "AND c.status = :status")
    Page<Voucher> searchVoucher(@Param("code") String code, @Param("campaign") Campaign campaign,
                                @Param("status") VoucherDto.VOUCHER_STATUS status,
                                Pageable pageable);

    @Query("SELECT v FROM Voucher v WHERE v.campaign IN :campaigns")
    Page<Voucher> findByCampaignIn(@Param("campaigns") List<Campaign> campaigns, Pageable pageable);

    @Query("SELECT v FROM Voucher v WHERE v.campaign = :campaign " +
            "AND c.status = :status")
    Page<Voucher> searchVoucher(@Param("campaign") Campaign campaign,
                                @Param("status") VoucherDto.VOUCHER_STATUS status, Pageable pageable);

    Page<Voucher> findByStatus(VoucherDto.VOUCHER_STATUS status, Pageable pageable);

    Optional<Voucher> findById(Long voucherId);

    Page<Voucher> findByCodeContainingIgnoreCaseAndUser(String code,
                                                        User user, Pageable pageable);

    Page<Voucher> findByUser(User user, Pageable pageable);

    @Query("SELECT COUNT(v) FROM Voucher v WHERE v.winnerUser IS NOT NULL")
    long countWinGames();

    @Query("SELECT COUNT(v) FROM Voucher")
    long countTotal();

    @Query("SELECT COUNT(v) FROM Voucher v WHERE v.status = com.baouyen.doan.dto.VoucherDto.VOUCHER_STATUS.USED")
    long countUsedVouchers();

    @Query("SELECT COUNT(v) FROM Voucher v WHERE v.expiredAt > :currentEpochValue")

    long countExpiredVouchers(@Param("currentEpochValue") long currentEpochValue);
}
