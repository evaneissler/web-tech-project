package edu.tcu.cs.backend.availability;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AvailabilityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AvailabilityService availabilityService;

    @Value("/api/v1/availability")
    String baseUrl;

    List<Availability> availabilities;

    @BeforeEach
    void setUp() {
        Game game = new Game();
        game.setId(1);
        game.setGameDate("2021-10-10");
        game.setVenue("Amon G Carter Stadium");
        game.setOpponent("SMU Mustangs");
        game.setIsFinalized(true);

        User user = new User();
        user.setId(1);
        user.setFirstName("John");

        Availability availability = new Availability();
        availability.setId(1);
        availability.setGame(game);
        availability.setUser(user);

        availabilities = new ArrayList<>();
        availabilities.add(availability);
    }

    @Test
    void testAddAvailabilitySuccess() throws Exception {
        Game game = new Game();
        game.setId(1);
        game.setGameDate("2021-10-10");
        game.setVenue("Amon G Carter Stadium");
        game.setOpponent("SMU Mustangs");
        game.setIsFinalized(true);

        User user = new User();
        user.setId(1);
        user.setFirstName("John");

        Availability availability = new Availability();
        availability.setId(1);
        availability.setGame(game);
        availability.setUser(user);

        String json = "{ \"userId\": 1, \"gameId\": 1, \"availability\": 1, \"comment\": \"Will be coming from another game\" }";

        given(this.availabilityService.save(Mockito.any(Availability.class))).willReturn(availability);

        this.mockMvc.perform(post(this.baseUrl).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.userId").value("1"))
                .andExpect(jsonPath("$.data.gameId").value("1"));
    }

    @Test
    void testAddAvailabilityValidationError() throws Exception {

        String json = "{}";

        this.mockMvc.perform(post(this.baseUrl).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.INVALID_ARGUMENT))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.userId").value("User id is required"))
                .andExpect(jsonPath("$.data.gameId").value("Game id is required"))
                .andExpect(jsonPath("$.data.availability").value("Availability is required"));
    }

    @Test
    void testFindAvailabilityByGameId() throws Exception {
        List<Availability> availability = new ArrayList<>();

        Game game = new Game();
        game.setId(1);
        game.setGameDate("2021-10-10");
        game.setVenue("Amon G Carter Stadium");
        game.setOpponent("SMU Mustangs");
        game.setIsFinalized(true);

        User user = new User();
        user.setId(1);
        user.setFirstName("John");

        Availability availability1 = new Availability();
        availability1.setId(1);
        availability1.setGame(game);
        availability1.setUser(user);

        availability.add(availability1);

        given(this.availabilityService.findAvailabilityByGameId(1)).willReturn(availability);

        this.mockMvc.perform(get(this.baseUrl + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find By Game Success"))
                .andExpect(jsonPath("$.data[0].userId").value("1"))
                .andExpect(jsonPath("$.data[0].gameId").value("1"));
    }

}
