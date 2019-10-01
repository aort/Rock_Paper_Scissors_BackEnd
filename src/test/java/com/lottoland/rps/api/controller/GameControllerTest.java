package com.lottoland.rps.api.controller;

import com.lottoland.rps.api.service.impl.GameService;
import com.lottoland.rps.controller.GameController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.lottoland.rps.api.GameHelper.getGameHelper;
import static com.lottoland.rps.api.GameHelper.getRoundHelper;
import static com.lottoland.rps.api.enums.Choice.PAPER;
import static com.lottoland.rps.api.enums.Choice.ROCK;
import static com.lottoland.rps.api.enums.Result.LOOSE;
import static com.lottoland.rps.controller.GameController.PLAYER_TWO_NAME;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = GameController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    public static final String REQUEST_MAPPING = "/api/v1/games";

    @Test
    public void shouldReturnNewEmptyGame() throws Exception {
        // Given
        when(gameService.start("AntonioTest", PLAYER_TWO_NAME)).thenReturn(
                getGameHelper(1L, 0, 1, 1, getRoundHelper(ROCK, PAPER, LOOSE)));
        // When
        ResultActions resultActions = start();

        // Then
        resultActions
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.playerOneName").value("AntonioTest"))
                .andExpect(jsonPath("$.playerOneScore").value(0))
                .andExpect(jsonPath("$.playerTwoScore").value(1))
                .andExpect(jsonPath("$.numberOfRounds").value(1));
    }

    private ResultActions start() throws Exception {
        // When
        return mockMvc.perform(post(REQUEST_MAPPING)
                .param("playerOneName", "AntonioTest")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""));
    }

}
