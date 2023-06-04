package com.baouyen.doan.service;

import com.baouyen.doan.converter.GameConverter;
import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.Game;
import com.baouyen.doan.entity.GamePlay;
import com.baouyen.doan.entity.Voucher;
import com.baouyen.doan.repository.GamePlayRepository;
import com.baouyen.doan.repository.GameRepository;
import com.baouyen.doan.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.baouyen.doan.service.CampaignServiceImp.GAME_RANDOM_DIGIT;

@Service
public class GameServiceImp implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameConverter gameConverter;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private GamePlayRepository gamePlayRepository;

    @Autowired
    private SecurityContextService securityContextService;

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
        Game crreatedGame = gameRepository.save(game);
    }

    @Override
    public void deleteGame() {

    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(c -> gameConverter.entityToDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public GamePlayResponse getVoucherGame(Long voucherId) {
        Optional<Voucher> voucherOtp = voucherRepository.findById(voucherId);
        if(!voucherOtp.isPresent()){
            throw new RuntimeException("Voucher was not found");
        }

        Set<Game> games = voucherOtp.get().getCampaign().getGames();
        int randomGameIndex = new Random().nextInt(games.size() - 1);
        GameType gameType = games.stream().skip(randomGameIndex).findFirst().get().getGameType();
        return new GamePlayResponse(gameType, GAME_RANDOM_DIGIT);
    }

    @Override
    public GameResult submitGame(Long voucherId, RedeemGameRequest request) {
        Optional<Voucher> voucherOptional = voucherRepository.findById(voucherId);
        GameResult gameResult = new GameResult();

        GamePlay gamePlay = new GamePlay();

        if(!voucherOptional.isPresent()){
            gameResult.setWin(false);
            gameResult.setFailedErrorMessage(
                    GamePlayDto.Error.VOUCHER_NOT_FOUND.getCode());
            gamePlay.setError(GamePlayDto.Error.VOUCHER_NOT_FOUND);
            gamePlayRepository.save(gamePlay);
            return gameResult;
        }

        Voucher voucher = voucherOptional.get();
        if(VoucherDto.VOUCHER_STATUS.USED == voucher.getStatus()){
            gameResult.setWin(false);
            gameResult.setFailedErrorMessage(GamePlayDto.Error.VOUCHER_HAS_USED.getCode());
            gamePlay.setError(GamePlayDto.Error.VOUCHER_HAS_USED);
            gamePlayRepository.save(gamePlay);
            return gameResult;
        }

        Long playAt = request.getPlayedAt();
        if(voucher.getExpiredAt() < playAt){
            gameResult.setWin(false);
            gameResult.setFailedErrorMessage(GamePlayDto.Error.VOUCHER_EXPIRED.getCode());
            gamePlay.setError(GamePlayDto.Error.VOUCHER_EXPIRED);
            gamePlayRepository.save(gamePlay);
            return gameResult;
        }

        String voucherGameRandomStr = voucher.getGameRandomString();
        if(GameType.LOTTERY_NUMBER.name().equals(request.getGameType())){
            voucherGameRandomStr = String.valueOf(voucher.getGameRandomNumber());
        }

        gamePlay.setPlayAt(playAt);
        gamePlayRepository.save(gamePlay);

        if(request.getPlayData().equals(voucherGameRandomStr)){
            voucher.setStatus(VoucherDto.VOUCHER_STATUS.USED);
            voucher.setWinnerUser(securityContextService.getCurrentLoginUser());
            voucherRepository.save(voucher);
            gameResult.setWin(true);
            gameResult.setWinMessage("Bingo! You win. Your voucher code is " + voucher.getCode());
            return gameResult;
        }

        gameResult.setFailedErrorMessage("You loose. Try again");
        gameResult.setWin(false);
        return gameResult;
    }
}
