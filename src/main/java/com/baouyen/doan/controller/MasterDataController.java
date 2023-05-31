package com.baouyen.doan.controller;

import com.baouyen.doan.dto.GameDto;
import com.baouyen.doan.dto.GameType;
import com.baouyen.doan.dto.MasterData;
import com.baouyen.doan.dto.VoucherDto;
import com.baouyen.doan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping()
public class MasterDataController {

    @Autowired
    private GameService gameService;

    @GetMapping("/master-data")
    @ResponseBody
    public MasterData getMasterData() {
        List<GameType> gameTypes = Arrays.asList(GameType.values());
        List<VoucherDto.VOUCHER_TYPE> voucherTypes = Arrays.asList(VoucherDto.VOUCHER_TYPE.values());

        List<GameDto> allGames = gameService.getAllGames();

        MasterData result = new MasterData();
        result.setGameTypes(gameTypes);
        result.setVoucherTypes(voucherTypes);
        result.setAllGames(allGames);

        return result;
    }
}
