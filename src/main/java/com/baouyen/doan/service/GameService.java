package com.baouyen.doan.service;

import com.baouyen.doan.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GameService {
    Page<GameDto> searchGame(SearchGameRequest request);
    void createGame(CreateGameRequest request);
    void deleteGame();

    List<GameDto> getAllGames();

    GamePlayResponse getVoucherGame(Long voucherId);

    GameResult submitGame(Long voucherId, RedeemGameRequest request);
}
