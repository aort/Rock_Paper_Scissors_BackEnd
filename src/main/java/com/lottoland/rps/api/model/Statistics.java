package com.lottoland.rps.api.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Statistics {

    private Long totalRoundsPlayed;

    private Integer totalWinsFirstPlayer;

    private Integer totalWinsSecondPlayer;

    private Integer totalDraws;

    @Builder
    public Statistics(Long totalRoundsPlayed, Integer totalWinsFirstPlayer, Integer totalWinsSecondPlayer, Integer totalDraws) {
        this.totalRoundsPlayed = totalRoundsPlayed;
        this.totalWinsFirstPlayer = totalWinsFirstPlayer;
        this.totalWinsSecondPlayer = totalWinsSecondPlayer;
        this.totalDraws = totalDraws;
    }

}
