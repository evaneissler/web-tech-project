package edu.tcu.cs.backend.crewSchedule.converter;

import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleCreateDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleDto;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.backend.user.UserService;
import edu.tcu.cs.backend.user.converter.UserToCrewMemberDtoConverter;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrewScheduleCreateDtoToCrewScheduleConverter {

    private final UserService userService;
    private final GameService gameService;

    public CrewScheduleCreateDtoToCrewScheduleConverter(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public List<CrewSchedule> convert(List<CrewScheduleCreateDto> source) {
        List<CrewSchedule> crewSchedule = new ArrayList<>();
        for (CrewScheduleCreateDto input : source) {
            CrewSchedule schedule = new CrewSchedule();
            schedule.setUser(this.userService.findById(input.userId()));
            schedule.setGame(this.gameService.findById(input.gameId()));
            schedule.setPosition(input.position());
            crewSchedule.add(schedule);
        }
        return crewSchedule;
    }
}