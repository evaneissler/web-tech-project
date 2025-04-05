package edu.tcu.cs.backend.gameSchedule;

import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameScheduleService {

    private final GameScheduleRepository gameScheduleRepository;

    public GameScheduleService(GameScheduleRepository gameScheduleRepository) {
        this.gameScheduleRepository = gameScheduleRepository;
    }

    public List<GameSchedule> findAll() {
        return gameScheduleRepository.findAll();
    }

    public GameSchedule save(GameSchedule newGameSchedule) { return this.gameScheduleRepository.save(newGameSchedule); }

    public GameSchedule findById(Integer id) {
        return this.gameScheduleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("gameSchedule", id));
    }
}
