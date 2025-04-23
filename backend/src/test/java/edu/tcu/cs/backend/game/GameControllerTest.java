package edu.tcu.cs.backend.game;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    GameService gameService;

    @MockitoBean
    GameScheduleService gameScheduleService;

    @Value("/api/v1/gameSchedule")
    String baseUrl;

    List<Game> games;
    GameSchedule gameSchedule;

    @BeforeEach
    void setUp() {
        this.gameSchedule = new GameSchedule();
        this.gameSchedule.setSport("Football Schedule");
        this.gameSchedule.setSeason("Fall");
        this.gameSchedule.setId(1);

        Game game1 = new Game();
        game1.setGameDate("2021-10-10");
        game1.setVenue("Amon G Carter Stadium");
        game1.setOpponent("SMU Mustangs");
        game1.setIsFinalized(true);
        game1.setId(1);
        game1.setGameSchedule(this.gameSchedule);

        Game game2 = new Game();
        game2.setGameDate("2021-10-10");
        game2.setVenue("Amon G Carter Stadium");
        game2.setOpponent("SMU Mustangs");
        game2.setIsFinalized(true);
        game2.setId(2);
        game2.setGameSchedule(this.gameSchedule);

        Game game3 = new Game();
        game3.setGameDate("2021-10-10");
        game3.setVenue("Amon G Carter Stadium");
        game3.setOpponent("SMU Mustangs");
        game3.setIsFinalized(true);
        game3.setId(3);
        game3.setGameSchedule(this.gameSchedule);

        this.games = new ArrayList<>();
        this.games.add(game1);
        this.games.add(game2);
        this.games.add(game3);
    }

    @Test
    void testFindAllGameSuccess() throws Exception {
        given(this.gameService.findAll()).willReturn(this.games);

        this.mockMvc.perform(get(this.baseUrl + "/games").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.games.size())))
                .andExpect(jsonPath("$.data[0].gameId").value(1))
                .andExpect(jsonPath("$.data[0].scheduleId").value(1))
                .andExpect(jsonPath("$.data[0].gameDate").value("2021-10-10"))
                .andExpect(jsonPath("$.data[0].venue").value("Amon G Carter Stadium"))
                .andExpect(jsonPath("$.data[0].opponent").value("SMU Mustangs"));
    }

    @Test
    void testFindGameByIdSuccess() throws Exception {
        given(this.gameService.findById(1)).willReturn(this.games.get(0));

        this.mockMvc.perform(get(this.baseUrl + "/game/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.gameId").value(1))
                .andExpect(jsonPath("$.data.scheduleId").value(1))
                .andExpect(jsonPath("$.data.gameDate").value("2021-10-10"))
                .andExpect(jsonPath("$.data.venue").value("Amon G Carter Stadium"))
                .andExpect(jsonPath("$.data.opponent").value("SMU Mustangs"));
    }

    @Test
    void testFindGameByIdNotFound() throws Exception {
        given(this.gameService.findById(1)).willThrow(new ObjectNotFoundException("game", 1));

        this.mockMvc.perform(get(this.baseUrl + "/game/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find game with id 1"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void testUpdateGameSuccess() throws Exception {
        Game oldGame = new Game();
        oldGame.setGameDate("2021-10-10");
        oldGame.setVenue("Amon G Carter Stadium");
        oldGame.setOpponent("SMU Mustangs");
        oldGame.setIsFinalized(true);
        oldGame.setGameSchedule(this.gameSchedule);
        oldGame.setId(1);

        Game newGame = new Game();
        newGame.setGameDate("2021-10-10");
        newGame.setVenue("Amon G Carter Stadium");
        newGame.setOpponent("SMU Mustangs");
        newGame.setIsFinalized(true);

        newGame.setGameSchedule(this.gameSchedule);
        newGame.setId(1);

        String json = this.objectMapper.writeValueAsString(oldGame);

        given(this.gameService.findById(1)).willReturn(oldGame);

        given(this.gameService.save(oldGame, 1)).willReturn(newGame);

        this.mockMvc.perform(put(this.baseUrl + "/game/1").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.gameId").value(1))
                .andExpect(jsonPath("$.data.scheduleId").value(1))
                .andExpect(jsonPath("$.data.gameDate").value("2021-10-10"))
                .andExpect(jsonPath("$.data.venue").value("Amon G Carter Stadium"))
                .andExpect(jsonPath("$.data.opponent").value("SMU Mustangs"));
    }

    @Test
    void testFindGamesByScheduleIdSuccess() throws Exception {
        given(this.gameService.findAllByScheduleId(1)).willReturn(this.games);

        this.mockMvc.perform(get(this.baseUrl + "/1/games").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find By Schedule Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.games.size())))
                .andExpect(jsonPath("$.data[0].gameId").value(1))
                .andExpect(jsonPath("$.data[0].scheduleId").value(1))
                .andExpect(jsonPath("$.data[0].gameDate").value("2021-10-10"))
                .andExpect(jsonPath("$.data[0].venue").value("Amon G Carter Stadium"))
                .andExpect(jsonPath("$.data[0].opponent").value("SMU Mustangs"));
    }

}
