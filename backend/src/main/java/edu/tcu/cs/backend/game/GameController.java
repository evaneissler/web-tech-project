package edu.tcu.cs.backend.game;

import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gameSchedule")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public Result findAllGames() {
        List<Game> foundGames = this.gameService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundGames);
    }

    @GetMapping("/game/{gameId}")
    public Result findById(@PathVariable int gameId) {
        Game found = this.gameService.findById(gameId);
        return new Result(true, StatusCode.SUCCESS, "Find Success", found);
    }

    @PutMapping("/game/{gameId}")
    public Result updateGame(@PathVariable int gameId, @RequestBody Game updatedGame) {
        Game oldGame = this.gameService.findById(gameId);
        oldGame.setName(updatedGame.getName());
        Game saved = this.gameService.save(oldGame, oldGame.getGameSchedule().getId());
        return new Result(true, StatusCode.SUCCESS, "Update Success", saved);
    }

    @GetMapping("/{scheduleId}/games")
    public Result findGamesBySchedule(@PathVariable int scheduleId) {
        List<Game> foundGames = this.gameService.findAllByScheduleId(scheduleId);
        return new Result(true, StatusCode.SUCCESS, "Find By Schedule Success", foundGames);
    }
}
