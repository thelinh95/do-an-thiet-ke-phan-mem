package com.baouyen.doan;

import com.baouyen.doan.dto.GameType;
import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.*;
import com.baouyen.doan.repository.CampaignRepository;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

import static com.baouyen.doan.dto.VoucherDto.VOUCHER_TYPE.TEN_PERCENT_DIS_COUNT;
import static com.baouyen.doan.util.RandomUtil.generateRandomCharacter;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
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
		createCampaignNoVoucher();
	}

	private void createCampaignNoVoucher() {
		char c = generateRandomCharacter();

		Campaign result = new Campaign();
		result.setName("name" + c);

		int month = new Random().nextInt(4) + 7;
		LocalDate startDate = LocalDate.of(2023, month, 28);
		LocalDate endDate = LocalDate.of(2023, month, 30);

		result.setStartDate(startDate);
		result.setEndDate(endDate);

		Partner createdPartner = createPartner();
		result.setPartner(createdPartner);

		createGame(result);

		result.setStatus(CampaignStatus.INITIAL);

		Campaign save = campaignRepository.save(result);
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
		createGame(result);

		result.setStatus(CampaignStatus.CREATED_VOUCHERS);

		Campaign save = campaignRepository.save(result);
	}

	private static void createVoucher(Campaign result) {
		char c = generateRandomCharacter();

		Voucher voucher = new Voucher();
		voucher.setDescription("voucher description" + c);
		voucher.setCode(UUID.randomUUID().toString());
		voucher.setType(TEN_PERCENT_DIS_COUNT);
		voucher.setStatus(VoucherDto.VOUCHER_STATUS.INITIAL);

		voucher.setGameRandomNumber(String.valueOf(RandomUtil.generateRandomNumber(6)));
		result.setVouchers(new HashSet<>(Collections.singletonList(voucher)));
	}

	private static void createGame(Campaign result) {
		char c = generateRandomCharacter();
		Set<Game> games = new HashSet<>();

		for(int i=0; i<5; i++) {
			Game game = new Game();
			int gameTypeInt = new Random().nextInt(2);
			game.setName("game" + c);
			game.setGameType(GameType.values()[gameTypeInt]);
			games.add(game);
		}

		result.setGames(games);
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
