package edu.tcu.cs.backend.game;


import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleRepository;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.User;
import org.junit.jupiter.api.BeforeEach;
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
class GameServiceTest {

    @Mock
    GameRepository gameRepository;

    @InjectMocks
    GameService gameService;

    List<Game> games;
    GameSchedule gameSchedule;

    @BeforeEach
    void setUp() {
        this.gameSchedule = new GameSchedule();
        gameSchedule.setSport("Football Schedule");
        gameSchedule.setSeason("Fall");

        Game game1 = new Game();
        game1.setGameDate("2021-10-10");
        game1.setVenue("Amon G Carter Stadium");
        game1.setOpponent("SMU Mustangs");
        game1.setIsFinalized(true);
        game1.setGameSchedule(this.gameSchedule);

        Game game2 = new Game();
        game2.setGameDate("2021-10-10");
        game2.setVenue("Amon G Carter Stadium");
        game2.setOpponent("SMU Mustangs");
        game2.setIsFinalized(true);
        game2.setGameSchedule(this.gameSchedule);

        Game game3 = new Game();
        game3.setGameDate("2021-10-10");
        game3.setVenue("Amon G Carter Stadium");
        game3.setOpponent("SMU Mustangs");
        game3.setIsFinalized(true);
        game3.setGameSchedule(this.gameSchedule);

        this.games = new ArrayList<>();
        this.games.add(game1);
        this.games.add(game2);
        this.games.add(game3);
    }

    @Test
    void testFindAllSuccess() {
        given(this.gameRepository.findAll()).willReturn(this.games);

        List<Game> games = this.gameService.findAll();

        assertThat(games.size()).isEqualTo(this.games.size());

        verify(this.gameRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        GameSchedule schedule = new GameSchedule();
        schedule.setSport("Football Schedule");
        schedule.setSeason("Fall");
        schedule.setId(1);

        Game game = new Game();
        game.setId(1);
        game.setGameDate("2021-10-10");
        game.setVenue("Amon G Carter Stadium");
        game.setOpponent("SMU Mustangs");
        game.setIsFinalized(true);
        game.setGameSchedule(schedule);

        given(this.gameRepository.findById(1)).willReturn(Optional.of(game)); // Define the behavior of the mock object.

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        Game returnedGame = this.gameService.findById(1);

        // Then. Assert expected outcomes.
        assertThat(returnedGame.getId()).isEqualTo(game.getId());
        assertThat(returnedGame.getGameDate()).isEqualTo(game.getGameDate());
        assertThat(returnedGame.getVenue()).isEqualTo(game.getVenue());
        assertThat(returnedGame.getOpponent()).isEqualTo(game.getOpponent());
        assertThat(returnedGame.getIsFinalized()).isEqualTo(game.getIsFinalized());
        assertThat(returnedGame.getGameSchedule().getId()).isEqualTo(game.getGameSchedule().getId());

        verify(this.gameRepository, times(1)).findById(1);
    }

    @Test
    void testFindAllByScheduleIdSuccess() {
        GameSchedule schedule = new GameSchedule();
        schedule.setSport("Football Schedule");
        schedule.setSeason("Fall");
        schedule.setId(1);

        Game game1 = new Game();
        game1.setId(1);
        game1.setGameDate("2021-10-10");
        game1.setVenue("Amon G Carter Stadium");
        game1.setOpponent("SMU Mustangs");
        game1.setIsFinalized(true);
        game1.setGameSchedule(schedule);

        Game game2 = new Game();
        game2.setId(1);
        game1.setGameDate("2021-10-10");
        game1.setVenue("Amon G Carter Stadium");
        game1.setOpponent("SMU Mustangs");
        game1.setIsFinalized(true);
        game2.setGameSchedule(schedule);

        List<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        given(this.gameRepository.findByGameScheduleId(1)).willReturn(games); // Define the behavior of the mock object.

        List<Game> returnedGames = this.gameService.findAllByScheduleId(1);

        assertThat(returnedGames.get(0).getId()).isEqualTo(games.get(0).getId());
        assertThat(returnedGames.get(0).getGameDate()).isEqualTo(games.get(0).getGameDate());
        assertThat(returnedGames.get(0).getVenue()).isEqualTo(games.get(0).getVenue());
        assertThat(returnedGames.get(0).getOpponent()).isEqualTo(games.get(0).getOpponent());
        assertThat(returnedGames.get(0).getIsFinalized()).isEqualTo(games.get(0).getIsFinalized());
        assertThat(returnedGames.get(0).getGameSchedule().getId()).isEqualTo(games.get(0).getGameSchedule().getId());

        verify(this.gameRepository, times(1)).findByGameScheduleId(1);
    }

}
