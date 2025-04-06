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
        game.setName("Game 1");

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
        game.setName("Game 1");

        User user = new User();
        user.setId(1);
        user.setFirstName("John");

        Availability availability = new Availability();
        availability.setId(1);
        availability.setGame(game);
        availability.setUser(user);

        String json = this.objectMapper.writeValueAsString(availability);

        given(this.availabilityService.save(Mockito.any(Availability.class))).willReturn(availability);

        this.mockMvc.perform(post(this.baseUrl).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.user.firstName").value("John"))
                .andExpect(jsonPath("$.data.game.name").value("Game 1"));
    }

    @Test
    void testFindAvailabilityByGameId() throws Exception {
        List<Availability> availability = new ArrayList<>();

        Game game = new Game();
        game.setId(1);
        game.setName("Game 1");

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
                .andExpect(jsonPath("$.data[0].user.firstName").value("John"))
                .andExpect(jsonPath("$.data[0].game.name").value("Game 1"));
    }

}
