package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import edu.tcu.cs.backend.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    @Test
    void testAddAvailability() {
        User user = new User();
        user.setId(1);

        Game game = new Game();
        game.setId(1);

        Availability availability = new Availability();
        availability.setUser(user);
        availability.setGame(game);

        // Simulate repository returns
        given(userRepository.findById(1)).willReturn(Optional.of(user));
        given(gameRepository.findById(1)).willReturn(Optional.of(game));

        given(this.availabilityRepository.save(availability)).willReturn(availability);

        // Act
        Availability saved = this.availabilityService.save(availability);

        // Assert
        assertThat(saved.getId()).isEqualTo(availability.getId());
        assertThat(saved.getUser().getId()).isEqualTo(user.getId());
        assertThat(saved.getGame().getId()).isEqualTo(game.getId());

        // Verify interactions
        verify(this.userRepository, times(1)).findById(1);
        verify(this.gameRepository, times(1)).findById(1);
    }

    @Test
    void testFindAvailabilityByGame() {
        List<Availability> availability = new ArrayList<>();

        User user = new User();
        user.setId(1);

        Game game = new Game();
        game.setId(1);

        Availability availability1 = new Availability();
        availability1.setUser(user);
        availability1.setGame(game);
        availability.add(availability1);

        given(availabilityRepository.findByGameId(1)).willReturn(availability);

        List<Availability> availabilityByGame = this.availabilityService.findAvailabilityByGameId(1);

        assertThat(availabilityByGame.size()).isEqualTo(1);
        assertThat(availabilityByGame.get(0).getUser().getId()).isEqualTo(user.getId());
        assertThat(availabilityByGame.get(0).getGame().getId()).isEqualTo(game.getId());

        verify(this.availabilityRepository, times(1)).findByGameId(1);
    }

}
