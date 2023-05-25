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

        return result;
    }


    public GameDto entityToDto(Game game) {
        GameDto result = new GameDto();
        result.setId(game.getId());
        result.setName(game.getName());

        return result;
    }
}
