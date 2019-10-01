package com.lottoland.rps.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lottoland.rps.api.enums.Choice;
import com.lottoland.rps.api.enums.Result;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Builder
    public Round(
            Choice playerOneChoice,
            Choice playerTwoChoice,
            Result result) {
        this.playerOneChoice = playerOneChoice;
        this.playerTwoChoice = playerTwoChoice;
        this.playerOneResult = result;
    }

}
