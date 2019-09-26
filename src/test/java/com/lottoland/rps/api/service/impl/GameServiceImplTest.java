package com.lottoland.rps.api.service.impl;


import com.lottoland.rps.api.dao.GameDAO;
import com.lottoland.rps.api.dao.RoundDAO;
import com.lottoland.rps.api.exceptions.GameNotFoundException;
import com.lottoland.rps.api.service.impl.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

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

}
