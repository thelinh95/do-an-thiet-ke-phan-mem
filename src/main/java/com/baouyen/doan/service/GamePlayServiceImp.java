package com.baouyen.doan.service;

import com.baouyen.doan.repository.GamePlayRepository;
import com.baouyen.doan.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayServiceImp implements GamePlayService{
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private GamePlayRepository gamePlayRepository;

    @Override
    public long getTotalUserPlayGame() {
        return gamePlayRepository.countPlayGames();
    }

    @Override
    public long getTotalUserWinGame() {
        return voucherRepository.countWinGames();
    }
}
