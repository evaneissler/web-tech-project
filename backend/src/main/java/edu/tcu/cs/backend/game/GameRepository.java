package edu.tcu.cs.backend.game;


import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByGameScheduleId(Integer gameScheduleId);
}

