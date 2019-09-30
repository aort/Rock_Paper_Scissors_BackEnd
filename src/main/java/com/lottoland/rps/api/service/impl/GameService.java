package com.lottoland.rps.api.service.impl;


import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.exceptions.GameNotFoundException;
import com.lottoland.rps.api.model.Game;
import com.lottoland.rps.api.model.Statistics;

public interface GameService {

    Game start(String playerOneName, String playerTwoName);

    Game getStatus(Long id) throws GameNotFoundException;

    Statistics getAllGamesStatus();

    Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException;
}
