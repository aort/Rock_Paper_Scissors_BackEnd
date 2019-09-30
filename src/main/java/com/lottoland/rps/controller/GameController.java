package com.lottoland.rps.controller;


import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.exceptions.GameNotFoundException;
import com.lottoland.rps.api.model.Game;
import com.lottoland.rps.api.model.Statistics;
import com.lottoland.rps.api.service.impl.GameService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Game")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    public static String PLAYER_TWO_NAME = "Antonio (Computer play random)";

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game start(
            @RequestParam("playerOneName") String playerOneName) {
        return gameService.start(playerOneName, PLAYER_TWO_NAME);
    }

    @GetMapping("/{gameId}")
    public Game getStatus(
            @PathVariable("gameId") Long id) throws GameNotFoundException {
        return gameService.getStatus(id);
    }

    @GetMapping("/")
    public Statistics getAllGamesStatus() {
        return gameService.getAllGamesStatus();
    }

    @PutMapping("/{gameId}")
    public Game play(
            @PathVariable("gameId") Long id,
            @RequestParam("choice") Choice playerOneChoice) throws GameNotFoundException {
        Choice playerTwoChoice = Choice.getRandom();
        return gameService.play(id, playerOneChoice, playerTwoChoice);
    }
}
