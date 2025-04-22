package edu.tcu.cs.backend.gameSchedule;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.dto.GameScheduleDto;
import edu.tcu.cs.backend.system.StatusCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class GameScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    GameScheduleService gameScheduleService;

    @MockitoBean
    GameService gameService;

    @MockitoBean
    GameScheduleDto gameScheduleDto;

    List<GameSchedule> gameSchedules;

    @Value("/api/v1")
    String baseUrl;

    @BeforeEach
    void setUp() {
        GameSchedule schedule1 = new GameSchedule();
        schedule1.setId(1);
        schedule1.setSport("Football Schedule");
        schedule1.setSeason("Fall");

        GameSchedule schedule2 = new GameSchedule();
        schedule2.setId(2);
        schedule2.setSport("Basketball Schedule");
        schedule2.setSeason("Winter");

        GameSchedule schedule3 = new GameSchedule();
        schedule3.setId(3);
        schedule3.setSport("Baseball Schedule");
        schedule3.setSeason("Spring");

        this.gameSchedules = new ArrayList<>();
        this.gameSchedules.add(schedule1);
        this.gameSchedules.add(schedule2);
        this.gameSchedules.add(schedule3);
    }

    @Test
    void testFindAllGameSchedulesSuccess() throws Exception {
        given(this.gameScheduleService.findAll()).willReturn(this.gameSchedules);

        this.mockMvc.perform(get(this.baseUrl + "/gameSchedule").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.gameSchedules.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].sport").value("Football Schedule"))
                .andExpect(jsonPath("$.data[0].season").value("Fall"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].sport").value("Basketball Schedule"))
                .andExpect(jsonPath("$.data[1].season").value("Winter"));

    }

    @Test
    void testAddGameScheduleSuccess() throws Exception {
        GameSchedule newSchedule = new GameSchedule();
        newSchedule.setId(4);
        newSchedule.setSport("Swimming");
        newSchedule.setSeason("Spring");

        String json = this.objectMapper.writeValueAsString(newSchedule);

        given(this.gameScheduleService.save(Mockito.any(GameSchedule.class))).willReturn(newSchedule);

        this.mockMvc.perform(post(this.baseUrl + "/gameSchedule").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").value(4))
                .andExpect(jsonPath("$.data.sport").value("Swimming"))
                .andExpect(jsonPath("$.data.season").value("Spring"));
    }


    @Test
    void testAddGameValidationError() throws Exception {

        String json = "{}";

        this.mockMvc.perform(post(this.baseUrl + "/gameSchedule/1/games").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.INVALID_ARGUMENT))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.gameDate").value("Game date is required"))
                .andExpect(jsonPath("$.data.venue").value("Venue is required"))
                .andExpect(jsonPath("$.data.opponent").value("Opponent is required"))
                .andExpect(jsonPath("$.data.isFinalized").value("Is finalized is required"));
    }

}
