package com.baouyen.doan.converter;

import com.baouyen.doan.dto.GamePlayDto;
import com.baouyen.doan.entity.GamePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamePlayConverter {
    @Autowired
    private GameConverter gameConverter;

    @Autowired
    private UserConverter userConverter;

    public GamePlay dtoToEntity(GamePlayDto gamePlayDto) {
        GamePlay result = new GamePlay();
        result.setId(gamePlayDto.getId());
        result.setGame(gameConverter.dtoToEntity(gamePlayDto.getGame()));
        result.setPlayAt(gamePlayDto.getPlayAt());
        result.setUser(userConverter.dtoToEntity(gamePlayDto.getUser()));
        result.setPlayData(gamePlayDto.getPlayData());
        return result;
    }


    public GamePlayDto entityToDto(GamePlay gamePlay) {
        GamePlayDto result = new GamePlayDto();
        result.setId(gamePlay.getId());
        result.setGame(gameConverter.entityToDto(gamePlay.getGame()));
        result.setPlayAt(gamePlay.getPlayAt());
        result.setUser(userConverter.entityToDto(gamePlay.getUser()));
        result.setPlayData(gamePlay.getPlayData());
        return result;
    }
}
