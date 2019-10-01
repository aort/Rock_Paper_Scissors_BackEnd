package com.lottoland.rps.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    private String playerOneName;

    private Integer playerOneScore;

    private String playerTwoName;

    private Integer playerTwoScore;

    private Integer numberOfRounds;

    @OneToMany
    private List<Round> rounds;

    public Game(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.numberOfRounds = 0;
        this.rounds = new ArrayList<>();
    }

    @Builder
    public Game(Long id, String playerOneName, String playerTwoName, Integer playerOneScore, Integer playerTwoScore, Integer numberOfRounds, List<Round> rounds) {
        this.id = id;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.numberOfRounds = numberOfRounds;
        this.rounds = rounds;
    }
}
