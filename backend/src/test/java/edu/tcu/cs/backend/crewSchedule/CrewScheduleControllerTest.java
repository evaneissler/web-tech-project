package edu.tcu.cs.backend.crewSchedule;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.AvailabilityService;
import edu.tcu.cs.backend.crewSchedule.converter.CrewScheduleToCrewScheduleDtoConverter;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleCreateDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleDto;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.user.User;
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
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CrewScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CrewScheduleService crewScheduleService;

    @Value("/api/v1/crewSchedule")
    String baseUrl;

    List<CrewSchedule> crewSchedules;

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
        user.setLastName("Brown");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("David");

        CrewSchedule crewSchedule = new CrewSchedule();
        crewSchedule.setId(1);
        crewSchedule.setGame(game);
        crewSchedule.setUser(user);
        crewSchedule.setPosition("Director");

        CrewSchedule crewSchedule1 = new CrewSchedule();
        crewSchedule1.setId(1);
        crewSchedule1.setGame(game);
        crewSchedule1.setUser(user2);
        crewSchedule1.setPosition("Producer");

        crewSchedules = new ArrayList<>();
        crewSchedules.add(crewSchedule);
        crewSchedules.add(crewSchedule1);
    }

    @Test
    void testAddCrewScheduleSuccess() throws Exception {
        CrewScheduleCreateDto crewScheduleCreateDto1 = new CrewScheduleCreateDto(
                0, 1, 1, "PRODUCER"
        );

        CrewScheduleCreateDto crewScheduleCreateDto2 = new CrewScheduleCreateDto(
                0, 2, 1, "DIRECTOR"
        );

        List<CrewScheduleCreateDto> crewSchedules = new ArrayList<>();
        crewSchedules.add(crewScheduleCreateDto1);
        crewSchedules.add(crewScheduleCreateDto2);


        String json = this.objectMapper.writeValueAsString(crewSchedules);

        given(this.crewScheduleService.save(Mockito.any(CrewSchedule.class))).willReturn(this.crewSchedules.get(0));

        this.mockMvc.perform(post(this.baseUrl + "/1").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.[0].fullName").value("John Brown"))
                .andExpect(jsonPath("$.data[0].gameId").value(1));
    }

    @Test
    void testAddCrewScheduleValidationError() throws Exception {

        String json = "[{}, {}]";

        this.mockMvc.perform(post(this.baseUrl + "/1").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.INVALID_ARGUMENT))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.userId").value("UserId is required"))
                .andExpect(jsonPath("$.data.position").value("Position is required"));
    }


    @Test
    void testFindCrewScheduleByGameId() throws Exception {
        given(this.crewScheduleService.findCrewScheduleByGameId(1)).willReturn(this.crewSchedules);

        this.mockMvc.perform(get(this.baseUrl + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.crewedMembers[0].fullName").value("John Brown"))
                .andExpect(jsonPath("$.data.venue").value("Amon G Carter Stadium"));
    }

}

