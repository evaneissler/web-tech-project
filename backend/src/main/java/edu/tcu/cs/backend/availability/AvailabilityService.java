package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<Availability> findAvailabilityByUserId(Integer id) throws ObjectNotFoundException {
        return this.availabilityRepository.findByUserId(id);
    }

    public List<Availability> findAvailabilityByGameId(Integer id) throws ObjectNotFoundException {
        return this.availabilityRepository.findByGameId(id);
    }

    public Availability findById(Integer id) throws ObjectNotFoundException {
        return this.availabilityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("availability", id));
    }

    public Availability save(Availability availability) {
        Integer userId = availability.getUser().getId();
        Integer gameId = availability.getGame().getId();

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));

        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("game", gameId));

        availability.setUser(user);
        availability.setGame(game);

        return this.availabilityRepository.save(availability);
    }

}
