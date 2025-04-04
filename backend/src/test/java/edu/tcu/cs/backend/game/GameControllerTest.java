package edu.tcu.cs.backend.game;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.system.StatusCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    GameController gameController;

    @MockitoBean
    GameScheduleService gameScheduleService;

    @Value("/api/v1/gameSchedule")
    String baseUrl;

    List<Game> games;
    GameSchedule gameSchedule;

    @BeforeEach
    void setUp() {
        this.gameSchedule = new GameSchedule();
        gameSchedule.setName("Football Schedule");

        Game game1 = new Game();
        game1.setName("Football");
        game1.setId(1);
        game1.setGameSchedule(this.gameSchedule);

        Game game2 = new Game();
        game2.setName("Basketball");
        game2.setId(2);
        game2.setGameSchedule(this.gameSchedule);

        Game game3 = new Game();
        game3.setName("Baseball");
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
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Football"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("Basketball"));
    }

    @Test
    void testFindGameByIdSuccess() throws Exception {
        given(this.gameService.findById(1)).willReturn(this.games.get(0));

        this.mockMvc.perform(get(this.baseUrl + "/game/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Football"));
    }

//    @Test
//    void testUpdateGameSuccess() throws Exception {
//        Game updatedGame = new Game();
//        updatedGame.setName("Football");
//        updatedGame.setId(1);
//        updatedGame.setGameSchedule(this.gameSchedule);
//
//        given(this.gameService.findById(1)).willReturn(this.games.get(0));
//
//        given(this.gameController.updateGame(eq("1"), Mockito.any(Artifact.class))).willReturn(updatedArtifact);
//
//
//        this.mockMvc.perform(put(this.baseUrl + "/game/1").accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.flag").value(true))
//                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
//                .andExpect(jsonPath("$.message").value("Find Success"))
//                .andExpect(jsonPath("$.data.id").value(1))
//                .andExpect(jsonPath("$.data.name").value("Football"));
//    }

}
