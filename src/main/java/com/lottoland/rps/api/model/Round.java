package com.lottoland.rps.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.enums.Result;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Round {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Choice playerOneChoice;

    private Choice playerTwoChoice;

    private Result playerOneResult;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Game game;

    public Round(
            Choice playerOneChoice,
            Choice playerTwoChoice,
            Result result,
            Game game) {
        this.playerOneChoice = playerOneChoice;
        this.playerTwoChoice = playerTwoChoice;
        this.playerOneResult = result;
        this.game = game;
    }
}
