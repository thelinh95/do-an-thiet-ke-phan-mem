package com.baouyen.doan.service;

import com.baouyen.doan.converter.GameConverter;
import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.Game;
import com.baouyen.doan.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImp implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameConverter gameConverter;
    @Override
    public Page<GameDto> searchGame(SearchGameRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<Game> result;
        if (name != null) {
            result = gameRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = gameRepository.findAll(pageable);
        }

        return result.map(c -> gameConverter.entityToDto(c));
    }

    @Override
    public void createGame(CreateGameRequest request) {
        Game game = gameConverter.requestDtoToEntity(request);
        gameRepository.save(game);
    }

    @Override
    public void deleteGame() {

    }
}
