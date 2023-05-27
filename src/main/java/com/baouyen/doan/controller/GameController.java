package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/search")
    @ResponseBody
    public Page<GameDto> searchGame(@RequestBody SearchGameRequest request) {
        String name = request.getName();
        Paginator paginator = request.getPaginator();

        return gameService.searchGame(request);
    }

    @PostMapping()
    @ResponseBody
    public Boolean createGame(@RequestBody @Valid CreateGameRequest request) {
        gameService.createGame(request);
        return true;
    }

    @GetMapping("/create")
    public String createGame() {
        return "game/create-game";
    }
}

