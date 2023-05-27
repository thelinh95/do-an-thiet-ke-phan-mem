package com.baouyen.doan.converter;

import com.baouyen.doan.dto.GameDto;
import com.baouyen.doan.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {
    public Game dtoToEntity(GameDto gameDto) {
        Game result = new Game();
        result.setId(gameDto.getId());
        result.setName(gameDto.getName());
        result.setGameType(gameDto.getGameType());
        return result;
    }

    public GameDto entityToDto(Game game) {
        GameDto result = new GameDto();
        result.setId(game.getId());
        result.setName(game.getName());
        result.setGameType(game.getGameType());
        return result;
    }
}
