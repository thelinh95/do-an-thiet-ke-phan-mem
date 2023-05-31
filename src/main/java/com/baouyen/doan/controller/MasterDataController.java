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
import java.util.stream.Collectors;

@Controller
@RequestMapping()
public class MasterDataController {

    public class MasterDataItem {
        public String code;
        public String text;
        public MasterDataItem(String code, String text) {
            this.code = code;
            this.text = text;
        }
    }

    @Autowired
    private GameService gameService;

    @GetMapping("/master-data")
    @ResponseBody
    public MasterData getMasterData() {
        List<GameType> gameTypesEnum = Arrays.asList(GameType.values());
        List<MasterDataItem> gameTypes = gameTypesEnum.stream().map(gt -> new MasterDataItem(gt.name(), gt.getCode()))
                .collect(Collectors.toList());

        List<VoucherDto.VOUCHER_TYPE> voucherTypesEnum = Arrays.asList(VoucherDto.VOUCHER_TYPE.values());
        List<MasterDataItem> voucherTypes = voucherTypesEnum.stream().map(vt -> new MasterDataItem(vt.name(), vt.getCode()))
                .collect(Collectors.toList());

        List<GameDto> allGames = gameService.getAllGames();

        MasterData result = new MasterData();
        result.setGameTypes(gameTypes);
        result.setVoucherTypes(voucherTypes);
        result.setAllGames(allGames);

        return result;
    }
}
