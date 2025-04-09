package edu.tcu.cs.backend.user;

import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    List<User> users;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");
        user1.setPassword("password");
        user1.setRole("USER");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane@smith.com");
        user2.setPassword("password");
        user2.setRole("ADMIN");

        User user3 = new User();
        user3.setFirstName("Jack");
        user3.setLastName("Brown");
        user3.setEmail("jack@brown.com");
        user3.setPassword("password");
        user3.setRole("USER");

        this.users = new ArrayList<>();
        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
    }

    @Test
    void testFindAllSuccess() {
        given(this.userRepository.findAll()).willReturn(this.users);

        List<User> actualUsers = this.userService.findAll();

        assertThat(actualUsers.size()).isEqualTo(this.users.size());

        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void testAddUserSuccess() {
        // Given
        User newUser = new User();
        newUser.setFirstName("Lily");
        newUser.setLastName("Thomas");
        newUser.setPassword("123456");
        newUser.setRole("USER");

        given(this.userRepository.save(newUser)).willReturn(newUser);

        User returnedUser = this.userService.save(newUser);

        assertThat(returnedUser.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(returnedUser.getLastName()).isEqualTo(newUser.getLastName());
        assertThat(returnedUser.getPassword()).isEqualTo(newUser.getPassword());
        assertThat(returnedUser.getRole()).isEqualTo(newUser.getRole());
        verify(this.userRepository, times(1)).save(newUser);
    }

    @Test
    void testFindByIdSuccess() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Lily");
        user.setLastName("Thomas");
        user.setPassword("123456");
        user.setRole("USER");

        given(this.userRepository.findById(1)).willReturn(Optional.of(user)); // Define the behavior of the mock object.

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        User returnedUser = this.userService.findById(1);

        // Then. Assert expected outcomes.
        assertThat(returnedUser.getId()).isEqualTo(user.getId());
        assertThat(returnedUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(returnedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(returnedUser.getRole()).isEqualTo(user.getRole());
        verify(this.userRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        given(this.userRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            User returnedUser = this.userService.findById(1);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find user with Id 1 :(");
        verify(this.userRepository, times(1)).findById(Mockito.any(Integer.class));
    }

    @Test
    void testFindByPositionSuccess() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Lily");
        user.setLastName("Thomas");
        user.setPassword("123456");
        user.setRole("USER");
        user.addPosition("Director");
        user.addPosition("Producer");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("David");
        user2.setLastName("Banks");
        user2.setPassword("123456");
        user2.setRole("ADMIN");
        user2.addPosition("Producer");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        given(this.userRepository.findAll()).willReturn(users);

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        List<User> returnedUsers = this.userService.findByPosition("Producer");

        // Then. Assert expected outcomes.
        assertThat(returnedUsers.size()).isEqualTo(2);
        assertThat(returnedUsers.get(0).getId()).isEqualTo(users.get(0).getId());
        assertThat(returnedUsers.get(0).getFirstName()).isEqualTo(users.get(0).getFirstName());
        assertThat(returnedUsers.get(0).getPositions().get(0)).isEqualTo(users.get(0).getPositions().get(0));

        assertThat(returnedUsers.get(1).getId()).isEqualTo(users.get(1).getId());
        assertThat(returnedUsers.get(1).getFirstName()).isEqualTo(users.get(1).getFirstName());
        assertThat(returnedUsers.get(1).getPositions().get(0)).isEqualTo(users.get(1).getPositions().get(0));

        verify(this.userRepository, times(1)).findAll();
    }
}

