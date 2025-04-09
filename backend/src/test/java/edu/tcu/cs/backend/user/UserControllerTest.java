package edu.tcu.cs.backend.user;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    UserService userService;

    List<User> users;

    @Value("/api/v1")
    String baseUrl;


    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Bryan");
        user1.setLastName("Jones");
        user1.setEmail("bryan@jones.com");
        user1.setPassword("password");
        user1.setRole("USER");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jeremy");
        user2.setLastName("Smith");
        user2.setEmail("jeremy@smith.com");
        user2.setPassword("password");
        user2.setRole("ADMIN");

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Phillip");
        user3.setLastName("Brown");
        user3.setEmail("phillip@brown.com");
        user3.setPassword("password");
        user3.setRole("USER");

        this.users = new ArrayList<>();
        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
    }

    @Test
    void testFindAllUsersSuccess() throws Exception {
        given(this.userService.findAll()).willReturn(this.users);

        this.mockMvc.perform(get(this.baseUrl + "/crewMember").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.users.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].firstName").value("Bryan"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].firstName").value("Jeremy"));
    }

    @Test
    void testAddUserSuccess() throws Exception {
        User user = new User();
        user.setId(4);
        user.setFirstName("Lily");
        user.setLastName("Thomas");
        user.setPassword("123456");
        user.setRole("USER");

        String json = this.objectMapper.writeValueAsString(user);

        given(this.userService.save(Mockito.any(User.class))).willReturn(user);

        this.mockMvc.perform(post(this.baseUrl + "/crewMember").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.firstName").value("Lily"))
                .andExpect(jsonPath("$.data.role").value("USER"));
    }

    @Test
    void testDisableUserSuccess() throws Exception {
        given(this.userService.findById(1)).willReturn(this.users.get(0));

        this.mockMvc.perform(put(this.baseUrl + "/crewMember/disable/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Disable Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.firstName").value("Bryan"))
                .andExpect(jsonPath("$.data.role").value("USER"))
                .andExpect(jsonPath("$.data.enabled").value(false));
    }

}