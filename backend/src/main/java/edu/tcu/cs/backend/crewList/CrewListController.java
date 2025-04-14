package edu.tcu.cs.backend.crewList;


import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.CrewScheduleService;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crewList")
public class CrewListController {

    private final CrewScheduleService crewScheduleService;

    public CrewListController(CrewScheduleService crewScheduleService) {
        this.crewScheduleService = crewScheduleService;
    }

    @GetMapping("/{gameId}")
    public Result getCrewListByGame(@PathVariable int gameId) {
        List<CrewSchedule> crewSchedules = this.crewScheduleService.findCrewScheduleByGameId(gameId);
        return new Result(true, StatusCode.SUCCESS, "Find By Game Success", crewSchedules);
    }

}
