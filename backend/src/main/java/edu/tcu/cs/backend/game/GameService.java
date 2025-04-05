package edu.tcu.cs.backend.game;

import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    private final GameScheduleService gameScheduleService;

    public GameService(GameRepository gameRepository, GameScheduleService gameScheduleService) {
        this.gameRepository = gameRepository;
        this.gameScheduleService = gameScheduleService;
    }

    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    public Game save(Game newGame, Integer scheduleId) {
        GameSchedule schedule = this.gameScheduleService.findById(scheduleId);
        newGame.setGameSchedule(schedule);
        return this.gameRepository.save(newGame);
    }

    public Game findById(Integer id) {
        return this.gameRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("game", id));
    }

    public List<Game> findAllByScheduleId(Integer scheduleId) {
        return this.gameRepository.findByGameScheduleId(scheduleId);
    }
}
