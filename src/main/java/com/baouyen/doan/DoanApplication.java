package com.baouyen.doan;

import com.baouyen.doan.dto.GameType;
import com.baouyen.doan.dto.StoreDto;
import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.entity.*;
import com.baouyen.doan.repository.CampaignRepository;
import com.baouyen.doan.repository.GameRepository;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.repository.StoreRepository;
import com.baouyen.doan.util.RandomUtil;
import jdk.internal.org.objectweb.asm.tree.ParameterNode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

import static com.baouyen.doan.dto.VoucherDto.VOUCHER_TYPE.TEN_PERCENT_DIS_COUNT;
import static com.baouyen.doan.util.RandomUtil.generateRandomString;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
public class DoanApplication {
	@Autowired
	GameRepository gameRepository;

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	StoreRepository	storeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DoanApplication.class, args);
	}

	@PostConstruct
	public void setupDbWithData() {
		createGame();
	}

	/**
	 * Create default 3 games.
	 */
	private void createGame() {
		for(int i=0; i<3; i++) {
			Game game = new Game();
			game.setName(GameType.values()[i].name());
			game.setGameType(GameType.values()[i]);
			gameRepository.save(game);
		}
	}

}
