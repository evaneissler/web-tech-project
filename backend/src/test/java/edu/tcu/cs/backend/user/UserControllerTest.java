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
import org.springframework.boot.test.mock.mockito.MockBean;
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
        user1.setPhoneNumber("1234567890");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jeremy");
        user2.setLastName("Smith");
        user2.setEmail("jeremy@smith.com");
        user2.setPassword("password");
        user2.setRole("ADMIN");
        user2.setPhoneNumber("1234567890");

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Phillip");
        user3.setLastName("Brown");
        user3.setEmail("phillip@brown.com");
        user3.setPassword("password");
        user3.setRole("USER");
        user3.setPhoneNumber("1234567890");

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
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.users.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].fullName").value("Bryan Jones"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].fullName").value("Jeremy Smith"));
    }

    @Test
    void testAddUserSuccess() throws Exception {
        User user = new User();
        user.setId(4);
        user.setFirstName("Lily");
        user.setLastName("Thomas");
        user.setPassword("123456");
        user.setRole("USER");
        user.setPhoneNumber("1234567890");
        user.setEmail("lily@thomas.brown.com");
        user.addPosition("Director");
        user.addPosition("Producer");

        String json = this.objectMapper.writeValueAsString(user);

        given(this.userService.save(Mockito.any(User.class))).willReturn(user);

        this.mockMvc.perform(post(this.baseUrl + "/crewMember").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.firstName").value("Lily"))
                .andExpect(jsonPath("$.data.lastName").value("Thomas"))
                .andExpect(jsonPath("$.data.email").value("lily@thomas.brown.com"))
                .andExpect(jsonPath("$.data.phoneNumber").value("1234567890"));
    }

    @Test
    void testAddUserValidationError() throws Exception {

        String json = "{}";

        this.mockMvc.perform(post(this.baseUrl + "/crewMember").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.INVALID_ARGUMENT))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.firstName").value("First name is required"))
                .andExpect(jsonPath("$.data.lastName").value("Last name is required"))
                .andExpect(jsonPath("$.data.email").value("Email is required"))
                .andExpect(jsonPath("$.data.phoneNumber").value("Phone number is required"))
                .andExpect(jsonPath("$.data.role").value("Role is required"))
                .andExpect(jsonPath("$.data.positions").value("Positions is required"));
    }

    @Test
    void testDisableUserSuccess() throws Exception {
        given(this.userService.findById(1)).willReturn(this.users.get(0));

        this.mockMvc.perform(put(this.baseUrl + "/crewMember/disable/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Disable Success"));
    }

}