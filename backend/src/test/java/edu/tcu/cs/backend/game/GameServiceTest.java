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
        gameSchedule.setName("Football Schedule");

        Game game1 = new Game();
        game1.setName("Football");
        game1.setGameSchedule(this.gameSchedule);

        Game game2 = new Game();
        game2.setName("Basketball");
        game2.setGameSchedule(this.gameSchedule);

        Game game3 = new Game();
        game3.setName("Baseball");
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
        schedule.setName("Football Schedule");
        schedule.setId(1);

        Game game = new Game();
        game.setId(1);
        game.setName("Football");
        game.setGameSchedule(schedule);

        given(this.gameRepository.findById(1)).willReturn(Optional.of(game)); // Define the behavior of the mock object.

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        Game returnedGame = this.gameService.findById(1);

        // Then. Assert expected outcomes.
        assertThat(returnedGame.getId()).isEqualTo(game.getId());
        assertThat(returnedGame.getName()).isEqualTo(game.getName());
        assertThat(returnedGame.getGameSchedule().getId()).isEqualTo(game.getGameSchedule().getId());

        verify(this.gameRepository, times(1)).findById(1);
    }

    @Test
    void testFindAllByScheduleIdSuccess() {
        GameSchedule schedule = new GameSchedule();
        schedule.setName("Football Schedule");
        schedule.setId(1);

        Game game1 = new Game();
        game1.setId(1);
        game1.setName("Football");
        game1.setGameSchedule(schedule);

        Game game2 = new Game();
        game2.setId(1);
        game2.setName("Football");
        game2.setGameSchedule(schedule);

        List<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        given(this.gameRepository.findByGameScheduleId(1)).willReturn(games); // Define the behavior of the mock object.

        List<Game> returnedGames = this.gameService.findAllByScheduleId(1);

        assertThat(returnedGames.get(0).getId()).isEqualTo(games.get(0).getId());
        assertThat(returnedGames.get(0).getName()).isEqualTo(games.get(0).getName());
        assertThat(returnedGames.get(0).getGameSchedule().getId()).isEqualTo(games.get(0).getGameSchedule().getId());

        verify(this.gameRepository, times(1)).findByGameScheduleId(1);
    }

}
