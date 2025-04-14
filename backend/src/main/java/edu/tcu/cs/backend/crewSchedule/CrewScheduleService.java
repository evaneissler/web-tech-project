package edu.tcu.cs.backend.crewSchedule;

import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.CrewScheduleRepository;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CrewScheduleService {

    private final CrewScheduleRepository crewScheduleRepository;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public CrewScheduleService(CrewScheduleRepository crewScheduleRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.crewScheduleRepository = crewScheduleRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<CrewSchedule> findCrewScheduleByUserId(Integer id) throws ObjectNotFoundException {
        return this.crewScheduleRepository.findByUserId(id);
    }

    public List<CrewSchedule> findCrewScheduleByGameId(Integer id) throws ObjectNotFoundException {
        return this.crewScheduleRepository.findByGameId(id);
    }

    public CrewSchedule findById(Integer id) throws ObjectNotFoundException {
        return this.crewScheduleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("availability", id));
    }

    public CrewSchedule save(CrewSchedule crewSchedule) {
        Integer userId = crewSchedule.getUser().getId();
        Integer gameId = crewSchedule.getGame().getId();

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));

        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("game", gameId));

        crewSchedule.setUser(user);
        crewSchedule.setGame(game);

        return this.crewScheduleRepository.save(crewSchedule);
    }

}
