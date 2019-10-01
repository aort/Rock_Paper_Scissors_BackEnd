package com.lottoland.rps.api.service.impl;


import com.lottoland.rps.api.dao.GameDAO;
import com.lottoland.rps.api.dao.RoundDAO;
import com.lottoland.rps.api.exceptions.GameNotFoundException;
import com.lottoland.rps.api.model.Game;
import com.lottoland.rps.api.service.impl.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.lottoland.rps.api.GameHelper.getGameHelper;
import static com.lottoland.rps.api.GameHelper.getRoundHelper;
import static com.lottoland.rps.api.enums.Choice.PAPER;
import static com.lottoland.rps.api.enums.Choice.ROCK;
import static com.lottoland.rps.api.enums.Result.LOOSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceImplTest {

    private GameDAO gameDAO;

    private RoundDAO roundDAO;

    private GameService classUnderTest;

    @Before
    public void setup() {
        gameDAO = mock(GameDAO.class);
        roundDAO = mock(RoundDAO.class);
        classUnderTest = new GameServiceImpl(gameDAO, roundDAO);
    }


    @Test(expected = GameNotFoundException.class)
    public void shouldThrowBadGameNotFoundException() throws GameNotFoundException {

        // given
        when(gameDAO.findById(any())).thenReturn(Optional.empty());

        // when
        classUnderTest.getStatus(1L);
    }

    @Test()
    public void shouldReturnOneGame() throws GameNotFoundException {

        // given
        when(gameDAO.findById(1L)).thenReturn(Optional.of(getGameHelper(1L, 0, 1,
                1, getRoundHelper(ROCK, PAPER, LOOSE))));

        // when
        Game gameFirstRound = classUnderTest.getStatus(1L);
        assertThat(gameFirstRound.getRounds().size()).isEqualTo(1);
        assertThat(gameFirstRound.getPlayerTwoScore()).isEqualTo(1);
    }

    @Test()
    public void shouldPlayAndReturnOneGame() throws GameNotFoundException {

        // given
        when(roundDAO.save(any())).thenReturn(getRoundHelper(ROCK, PAPER, LOOSE));
        when(gameDAO.save(any())).thenReturn(getGameHelper(1L, 0, 1, 1, getRoundHelper(ROCK, PAPER, LOOSE)));
        when(gameDAO.findById(1L)).thenReturn(Optional.of(getGameHelper(1L, 0, 0, 0, getRoundHelper(ROCK, PAPER, LOOSE))));

        // when
        Game gameFirstRound = classUnderTest.play(1L, ROCK, PAPER);
        assertThat(gameFirstRound.getRounds().size()).isEqualTo(1);
        assertThat(gameFirstRound.getPlayerTwoScore()).isEqualTo(1);
    }

}
