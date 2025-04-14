package edu.tcu.cs.backend.crewSchedule;

import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crewSchedule")
public class CrewScheduleController {

    private final CrewScheduleService crewScheduleService;

    public CrewScheduleController(CrewScheduleService crewScheduleService) {
        this.crewScheduleService = crewScheduleService;
    }

    @PostMapping("/{gameId}")
    public Result addCrewSchedule(@RequestBody List<CrewSchedule> crewSchedules, @PathVariable int gameId) {
        List<CrewSchedule> savedSchedules = new ArrayList<>();
        for (CrewSchedule crewSchedule : crewSchedules) {
            CrewSchedule newCrewSchedule = this.crewScheduleService.save(crewSchedule);
            savedSchedules.add(newCrewSchedule);
        }
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedSchedules);
    }

    @GetMapping("/{gameId}")
    public Result getCrewScheduleByGame(@PathVariable int gameId) {
        List<CrewSchedule> crewSchedules = this.crewScheduleService.findCrewScheduleByGameId(gameId);
        return new Result(true, StatusCode.SUCCESS, "Find By Game Success", crewSchedules);
    }

}
