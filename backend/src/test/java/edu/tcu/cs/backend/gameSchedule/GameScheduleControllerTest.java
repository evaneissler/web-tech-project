package edu.tcu.cs.backend.gameSchedule;


import com.fasterxml.jackson.databind.ObjectMapper;
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

    List<GameSchedule> gameSchedules;

    @Value("/api/v1")
    String baseUrl;


    @BeforeEach
    void setUp() {
        GameSchedule schedule1 = new GameSchedule();
        schedule1.setId(1);
        schedule1.setName("Football Schedule");

        GameSchedule schedule2 = new GameSchedule();
        schedule2.setId(2);
        schedule2.setName("Basketball Schedule");

        GameSchedule schedule3 = new GameSchedule();
        schedule3.setId(3);
        schedule3.setName("Baseball Schedule");

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
                .andExpect(jsonPath("$.data[0].name").value("Football Schedule"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("Basketball Schedule"));
    }

    @Test
    void testAddGameScheduleSuccess() throws Exception {
        GameSchedule newSchedule = new GameSchedule();
        newSchedule.setId(4);
        newSchedule.setName("Swimming Schedule");

        String json = this.objectMapper.writeValueAsString(newSchedule);

        given(this.gameScheduleService.save(Mockito.any(GameSchedule.class))).willReturn(newSchedule);

        this.mockMvc.perform(post(this.baseUrl + "/gameSchedule").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").value(4))
                .andExpect(jsonPath("$.data.name").value("Swimming Schedule"));
    }
}
