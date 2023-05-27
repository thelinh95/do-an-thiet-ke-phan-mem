package com.baouyen.doan.controller;

import com.baouyen.doan.dto.GameType;
import com.baouyen.doan.dto.MasterData;
import com.baouyen.doan.dto.VoucherDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String home() {
        return "admin/index";
    }

    @GetMapping("/campaign")
    public String campaign() {
        return "admin/campaign";
    }
    @GetMapping("/partner")
    public String partner() {
        return "admin/partner";
    }

    @GetMapping("/game")
    public String game() {
        return "admin/game";
    }

    @GetMapping("/statistic")
    public String statistic() {
        return "admin/statistic";
    }

    @GetMapping("/master-data")
    @ResponseBody
    public MasterData getMasterData() {
        List<GameType> gameTypes = Arrays.asList(GameType.values());
        List<VoucherDto.VOUCHER_TYPE> voucherTypes = Arrays.asList(VoucherDto.VOUCHER_TYPE.values());
        MasterData result = new MasterData();
        result.setGameTypes(gameTypes);
        result.setVoucherTypes(voucherTypes);
        return result;
    }
}

