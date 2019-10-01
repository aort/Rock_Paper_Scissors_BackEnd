package com.lottoland.rps.api;

import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.enums.Result;
import com.lottoland.rps.api.model.Game;
import com.lottoland.rps.api.model.Round;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {

    public static Game getGameHelper(Long id, Integer playerOneScore, Integer playerTwoScore, Integer numberOfRounds, Round round) {
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        return Game.builder()
                .id(id)
                .numberOfRounds(numberOfRounds)
                .playerOneName("AntonioTest")
                .playerOneScore(playerOneScore)
                .playerTwoScore(playerTwoScore)
                .rounds(rounds)
                .build();
    }

    public static Round getRoundHelper(Choice playerOneChoice, Choice playerTwoChoice, Result result) {
        return Round.builder()
                .playerOneChoice(playerOneChoice)
                .playerTwoChoice(playerTwoChoice)
                .result(result)
                .build();
    }
}
