package edu.tcu.cs.backend.crewSchedule;


import edu.tcu.cs.backend.availability.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewScheduleRepository extends JpaRepository<CrewSchedule, Integer> {
    List<CrewSchedule> findByUserId(Integer userId);
    List<CrewSchedule> findByGameId(Integer userId);
}

