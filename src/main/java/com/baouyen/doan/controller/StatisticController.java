package com.baouyen.doan.controller;

import com.baouyen.doan.service.CampaignService;
import com.baouyen.doan.service.GamePlayService;
import com.baouyen.doan.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/statistic")
public class StatisticController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private GamePlayService gamePlayService;

    @GetMapping
    public String searchStatistic(Model model) {
        long totalVouchers = voucherService.getTotalVoucher();
        long numberUsedVouchers = voucherService.getNumberUsedVouchers();
        long numberExpiredVouchers = voucherService.getNumberExpiredVouchers();

        long totalUserPlayGame = gamePlayService.getTotalUserPlayGame();
        long totalUserWinGame = gamePlayService.getTotalUserWinGame();

        model.addAttribute("totalVouchers", totalVouchers);
        model.addAttribute("numberUsedVouchers", numberUsedVouchers);
        model.addAttribute("numberExpiredVouchers", numberExpiredVouchers);

        model.addAttribute("totalUserPlayGame", totalUserPlayGame);
        model.addAttribute("totalUserWinGame", totalUserWinGame);
        return "admin/statistic";
    }

}

