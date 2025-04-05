package edu.tcu.cs.backend.gameSchedule;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gameSchedule")
public class GameScheduleController {
    private final GameScheduleService gameScheduleService;
    private final GameService gameService;

    public GameScheduleController(GameScheduleService gameScheduleService, GameService gameService) {
        this.gameScheduleService = gameScheduleService;
        this.gameService = gameService;
    }

    @GetMapping
    public Result findAllGameSchedules() {
        List<GameSchedule> foundSchedules = this.gameScheduleService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundSchedules);
    }

    @PostMapping
    public Result addGameSchedule(@RequestBody GameSchedule newSchedule) {
        GameSchedule savedSchedule = this.gameScheduleService.save(newSchedule);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedSchedule);
    }

    @PostMapping("/{scheduleId}/games")
    public Result addGameToSchedule(@RequestBody Game newGame, @PathVariable Integer scheduleId) {
        Game savedGame = this.gameService.save(newGame, scheduleId);
        return new Result(true, StatusCode.SUCCESS, "Add Game Success", savedGame);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) {
        GameSchedule foundSchedule = this.gameScheduleService.findById(id);
        return new Result(true, StatusCode.SUCCESS, "Find Success", foundSchedule);
    }
}
