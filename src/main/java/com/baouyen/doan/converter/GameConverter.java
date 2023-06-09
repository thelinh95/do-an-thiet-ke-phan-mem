package com.baouyen.doan.converter;

import com.baouyen.doan.dto.CreateGameRequest;
import com.baouyen.doan.dto.GameDto;
import com.baouyen.doan.dto.GameType;
import com.baouyen.doan.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {
    public Game requestDtoToEntity(CreateGameRequest createGameRequest) {
        Game result = new Game();
        result.setName(createGameRequest.getName());
        result.setGameType(GameType.valueOf(
                createGameRequest.getGameType()));
        return result;
    }

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
