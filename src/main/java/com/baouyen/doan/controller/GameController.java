package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping()
    public Page<GameDto> searchCampaign(@RequestBody SearchGameRequest request) {
        String name = request.getName();
        Paginator paginator = request.getPaginator();

        return gameService.searchGame(request);
    }

}

