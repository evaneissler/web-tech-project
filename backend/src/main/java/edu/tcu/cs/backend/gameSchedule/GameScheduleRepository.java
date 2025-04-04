package edu.tcu.cs.backend.gameSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameScheduleRepository extends JpaRepository<GameSchedule, Integer> {
}
