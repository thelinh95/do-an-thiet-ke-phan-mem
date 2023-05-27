package com.baouyen.doan.service;

import com.baouyen.doan.dto.GameDto;
import com.baouyen.doan.dto.SearchGameRequest;
import org.springframework.data.domain.Page;

public interface GameService {
    Page<GameDto> searchGame(SearchGameRequest request);
    void createGame();
    void deleteGame();
}
