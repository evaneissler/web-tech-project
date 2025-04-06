package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    List<Availability> findByUserId(Integer userId);
    List<Availability> findByGameId(Integer userId);
}
