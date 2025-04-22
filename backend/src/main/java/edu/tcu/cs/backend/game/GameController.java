package edu.tcu.cs.backend.game;

import edu.tcu.cs.backend.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.backend.game.dto.GameDto;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gameSchedule")
public class GameController {

    private final GameService gameService;
    private final GameToGameDtoConverter gameToGameDtoConverter;

    public GameController(GameService gameService, GameToGameDtoConverter gameToGameDtoConverter) {
        this.gameService = gameService;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
    }

    @GetMapping("/games")
    public Result findAllGames() {
        List<Game> foundGames = this.gameService.findAll();
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : foundGames) {
            GameDto gameDto = this.gameToGameDtoConverter.convert(game);
            gameDtos.add(gameDto);
        }
        return new Result(true, StatusCode.SUCCESS, "Find All Success", gameDtos);
    }

    @GetMapping("/game/{gameId}")
    public Result findById(@PathVariable int gameId) {
        Game found = this.gameService.findById(gameId);
        GameDto gameDto = this.gameToGameDtoConverter.convert(found);
        return new Result(true, StatusCode.SUCCESS, "Find Success", gameDto);
    }

    @PutMapping("/game/{gameId}")
    public Result updateGame(@PathVariable int gameId, @RequestBody Game updatedGame) {
        Game oldGame = this.gameService.findById(gameId);
        oldGame.setGameDate(updatedGame.getGameDate());
        oldGame.setVenue(updatedGame.getVenue());
        oldGame.setOpponent(updatedGame.getOpponent());
        oldGame.setIsFinalized(updatedGame.getIsFinalized());
        Game saved = this.gameService.save(oldGame, oldGame.getGameSchedule().getId());
        GameDto gameDto = this.gameToGameDtoConverter.convert(saved);
        return new Result(true, StatusCode.SUCCESS, "Update Success", gameDto);
    }

    @GetMapping("/{scheduleId}/games")
    public Result findGamesBySchedule(@PathVariable int scheduleId) {
        List<Game> foundGames = this.gameService.findAllByScheduleId(scheduleId);
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : foundGames) {
            GameDto gameDto = this.gameToGameDtoConverter.convert(game);
            gameDtos.add(gameDto);
        }
        return new Result(true, StatusCode.SUCCESS, "Find By Schedule Success", gameDtos);
    }
}
