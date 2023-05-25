package com.baouyen.doan;

import com.baouyen.doan.entity.Campaign;
import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.entity.Voucher;
import com.baouyen.doan.repository.CampaignRepository;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static com.baouyen.doan.dto.VoucherDto.VOUCHER_TYPE.TEN_PERCENT_DIS_COUNT;
import static com.baouyen.doan.util.RandomUtil.generateRandomCharacter;

@SpringBootApplication
public class DoanApplication {
	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	PartnerRepository partnerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DoanApplication.class, args);


	}

	@PostConstruct
	public void setupDbWithData() {
		createCampaign();
		createCampaign();
		createCampaign();
		createCampaign();
		createCampaign();
	}

	private void createCampaign() {
		char c = generateRandomCharacter();

		Campaign result = new Campaign();
		result.setName("name" + c);

		int month = new Random().nextInt(4) + 5;
		LocalDate startDate = LocalDate.of(2023, month, 22);
		LocalDate endDate = LocalDate.of(2023, month, 30);

		result.setStartDate(startDate);
		result.setEndDate(endDate);

		Partner createdPartner = createPartner();
		result.setPartner(createdPartner);

		// init voucher
		createVoucher(result);

		campaignRepository.save(result);
	}

	private static void createVoucher(Campaign result) {
		char c = generateRandomCharacter();

		Voucher voucher = new Voucher();
		voucher.setDescription("voucher description" + c);
		voucher.setQuantity(30);
		voucher.setType(TEN_PERCENT_DIS_COUNT);

		voucher.setGameRandomNumber(String.valueOf(RandomUtil.generateRandomNumber(6)));
		result.setVouchers(Arrays.asList(voucher));
	}

	private Partner createPartner() {
		char c = generateRandomCharacter();

		Partner partner = new Partner();
		partner.setName("partner name" + c);
		partner.setProvinceAddress("province" + c);
		partner.setWardAddress("ward" + c);
		partner.setDistrictAddress("district" + c);
		partner.setStreetAddress("street" + c);
		Partner createdPartner = partnerRepository.save(partner);
		return createdPartner;
	}


}
