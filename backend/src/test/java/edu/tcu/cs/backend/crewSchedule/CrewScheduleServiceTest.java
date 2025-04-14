package edu.tcu.cs.backend.crewSchedule;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.AvailabilityRepository;
import edu.tcu.cs.backend.availability.AvailabilityService;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CrewScheduleServiceTest {

    @Mock
    private CrewScheduleRepository crewScheduleRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CrewScheduleService crewScheduleService;

    @Test
    void testAddCrewSchedule() {
        User user = new User();
        user.setId(1);

        Game game = new Game();
        game.setId(1);

        CrewSchedule crewSchedule = new CrewSchedule();
        crewSchedule.setUser(user);
        crewSchedule.setGame(game);

        // Simulate repository returns
        given(userRepository.findById(1)).willReturn(Optional.of(user));
        given(gameRepository.findById(1)).willReturn(Optional.of(game));

        given(this.crewScheduleRepository.save(crewSchedule)).willReturn(crewSchedule);

        // Act
        CrewSchedule saved = this.crewScheduleService.save(crewSchedule);

        // Assert
        assertThat(saved.getId()).isEqualTo(crewSchedule.getId());
        assertThat(saved.getUser().getId()).isEqualTo(user.getId());
        assertThat(saved.getGame().getId()).isEqualTo(game.getId());

        // Verify interactions
        verify(this.userRepository, times(1)).findById(1);
        verify(this.gameRepository, times(1)).findById(1);
    }

    @Test
    void testFindCrewScheduleByGame() {
        List<CrewSchedule> crewSchedules = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);

        Game game1 = new Game();
        game1.setId(1);

        CrewSchedule crewSchedule1 = new CrewSchedule();
        crewSchedule1.setUser(user1);
        crewSchedule1.setGame(game1);

        User user2 = new User();
        user2.setId(2);

        CrewSchedule crewSchedule2 = new CrewSchedule();
        crewSchedule2.setUser(user2);
        crewSchedule2.setGame(game1);

        crewSchedules.add(crewSchedule1);
        crewSchedules.add(crewSchedule2);


        given(crewScheduleRepository.findByGameId(1)).willReturn(crewSchedules);

        List<CrewSchedule> crewScheduleByGame = this.crewScheduleService.findCrewScheduleByGameId(1);

        assertThat(crewScheduleByGame.size()).isEqualTo(2);
        assertThat(crewScheduleByGame.get(0).getUser().getId()).isEqualTo(user1.getId());
        assertThat(crewScheduleByGame.get(0).getGame().getId()).isEqualTo(game1.getId());
        assertThat(crewScheduleByGame.get(1).getUser().getId()).isEqualTo(user2.getId());
        assertThat(crewScheduleByGame.get(1).getGame().getId()).isEqualTo(game1.getId());

        verify(this.crewScheduleRepository, times(1)).findByGameId(1);
    }

}
