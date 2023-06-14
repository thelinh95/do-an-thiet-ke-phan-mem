package com.baouyen.doan.controller;

import com.baouyen.doan.dto.*;
import com.baouyen.doan.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.baouyen.doan.service.CampaignServiceImp.GAME_RANDOM_DIGIT;

@Controller
public class GameController extends BaseController{
    @Autowired
    private GameService gameService;

    @PostMapping("/admin/games/search")
    @ResponseBody
    public Page<GameDto> searchGame(@RequestBody SearchGameRequest request) {
        return gameService.searchGame(request);
    }

    @PostMapping("/admin/games")
    @ResponseBody
    public Boolean createGame(@RequestBody @Valid CreateGameRequest request) {
        gameService.createGame(request);
        return true;
    }

    @GetMapping("/admin/games/create")
    public String createGame(HttpServletRequest request, Model model) {
        addRemoteUserToModel(model, request);
        return "game/create-game";
    }

    /**
     * Load game to redeem voucher
     * @return
     */
    @GetMapping("/voucher/{voucherId}/redeem")
    public String redeemVoucher(@PathVariable Long voucherId, Model model,
            HttpServletRequest request) {
        addRemoteUserToModel(model, request);
        GameType gameType = gameService.getVoucherGame(voucherId).getGameType();
        String gameView = "";
        switch (gameType) {
            case LOTTERY_NUMBER:
                gameView = "game-lottery-digit";
                break;
            case LOTTERY_CHARACTER:
                gameView = "game-lottery-character";
                break;
            case TERIS:
                gameView = "game-teris";
                break;
        }

        model.addAttribute("voucherId", voucherId);
        model.addAttribute("gameRandomDigit", GAME_RANDOM_DIGIT);
        model.addAttribute("gameType", gameType.name());
        String gameViewPrefix = "game/";
        return gameViewPrefix + gameView;
    }

    @PostMapping("/voucher/{voucherId}/redeem")
    @ResponseBody
    public GameResult submitGame(@PathVariable Long voucherId, @RequestBody RedeemGameRequest request){
        return gameService.submitGame(voucherId, request);
    }
}

