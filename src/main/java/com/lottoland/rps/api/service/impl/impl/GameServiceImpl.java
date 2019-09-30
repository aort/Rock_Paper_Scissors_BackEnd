package com.lottoland.rps.api.service.impl.impl;


import com.lottoland.rps.api.dao.GameDAO;
import com.lottoland.rps.api.dao.RoundDAO;
import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.enums.Result;
import com.lottoland.rps.api.exceptions.GameNotFoundException;
import com.lottoland.rps.api.model.Game;
import com.lottoland.rps.api.model.Round;
import com.lottoland.rps.api.model.Statistics;
import com.lottoland.rps.api.service.impl.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final GameDAO gameDAO;

    private final RoundDAO roundDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO, RoundDAO roundDAO) {
        this.gameDAO = gameDAO;
        this.roundDAO = roundDAO;
    }

    @Override
    public Game start(String playerOneName, String playerTwoName) {
        Game game = createGame(playerOneName, playerTwoName);
        return gameDAO.save(game);
    }

    @Override
    public Game getStatus(Long id) throws GameNotFoundException {
        return gameDAO.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    @Override
    public Statistics getAllGamesStatus() {
        List<Game> totalListOfGames = gameDAO.findAll();

        Integer totalRounds = totalListOfGames.stream()
                .map(x -> x.getRounds().size())
                .reduce(0, Integer::sum);

        Integer totalWinOne = totalListOfGames.stream()
                .map(x -> x.getPlayerOneScore())
                .reduce(0, Integer::sum);

        Integer totalWinTwo = totalListOfGames.stream()
                .map(x -> x.getPlayerTwoScore())
                .reduce(0, Integer::sum);

        return Statistics.builder()
                .totalDraws(totalRounds - totalWinOne - totalWinTwo)
                .totalRoundsPlayed(Long.valueOf(totalRounds))
                .totalWinsFirstPlayer(totalWinOne)
                .totalWinsSecondPlayer(totalWinTwo)
                .build();
    }

    @Override
    public Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException {
        Game game = getStatus(id);
        Round round = createRound(playerOneChoice, playerTwoChoice, game);
        addRound(game, round);
        incrementScore(round, game);
        return gameDAO.save(game);
    }

    private void addRound(Game game, Round round) {
        if (CollectionUtils.isEmpty(game.getRounds())) {
            List<Round> rounds = new ArrayList<>();
            game.setRounds(rounds);
        }
        game.getRounds().add(round);
        game.setNumberOfRounds(game.getRounds().size());
    }

    private Round createRound(Choice playerOneChoice, Choice playerTwoChoice, Game game) {
        Result result = evaluateChoices(playerOneChoice, playerTwoChoice);
        Round round = new Round(playerOneChoice, playerTwoChoice, result, game);
        return roundDAO.save(round);
    }

    private void incrementScore(Round round, Game game) {
        if (round.getPlayerOneResult().equals(Result.WIN)) {
            game.setPlayerOneScore(game.getPlayerOneScore() + 1);
        } else if (round.getPlayerOneResult().equals(Result.LOOSE)) {
            game.setPlayerTwoScore(game.getPlayerTwoScore() + 1);
        }
    }

    private Result evaluateChoices(Choice playerOne, Choice playerTwo) {
        validateChoice(playerOne);
        Result result = Result.DRAW;
        if (playerOne.isBetterThan(playerTwo)) {
            result = Result.WIN;
        } else if (playerTwo.isBetterThan(playerOne)) {
            result = Result.LOOSE;
        }
        return result;
    }

    private Game createGame(String playerOneName, String playerTwoName) {
        Game game = new Game(playerOneName, playerTwoName);
        return game;
    }

    private void validateChoice(Choice playerOneChoice) {
        if (Objects.isNull(playerOneChoice)) {
            String msg = String.format("Choice cannot be empty, playerOneChoice: {}", playerOneChoice);
            throw new IllegalArgumentException(msg);
        }
    }

}
