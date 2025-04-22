package edu.tcu.cs.backend.crewSchedule;

import edu.tcu.cs.backend.crewSchedule.converter.CrewScheduleCreateDtoToCrewScheduleConverter;
import edu.tcu.cs.backend.crewSchedule.converter.CrewScheduleToCrewScheduleDtoConverter;
import edu.tcu.cs.backend.crewSchedule.converter.CrewScheduleToCrewedUserDtoConverter;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleCreateDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewedUser;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.gameSchedule.converter.GameScheduleToGameScheduleDtoConverter;
import edu.tcu.cs.backend.gameSchedule.dto.GameScheduleDto;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.converter.UserToCrewMemberDtoConverter;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crewSchedule")
@Validated
public class CrewScheduleController {

    private final GameRepository gameRepository;
    private final CrewScheduleService crewScheduleService;
    private final CrewScheduleToCrewScheduleDtoConverter crewScheduleToCrewScheduleDtoConverter;
    private final CrewScheduleCreateDtoToCrewScheduleConverter crewScheduleCreateDtoToCrewScheduleConverter;
    private final CrewScheduleToCrewedUserDtoConverter crewScheduleToCrewedUserDtoConverter;

    public CrewScheduleController(GameRepository gameRepository, CrewScheduleService crewScheduleService, CrewScheduleToCrewScheduleDtoConverter crewScheduleToCrewScheduleDtoConverter, CrewScheduleCreateDtoToCrewScheduleConverter crewScheduleCreateDtoToCrewScheduleConverter, CrewScheduleToCrewedUserDtoConverter crewScheduleToCrewedUserDtoConverter) {
        this.gameRepository = gameRepository;
        this.crewScheduleService = crewScheduleService;
        this.crewScheduleToCrewScheduleDtoConverter = crewScheduleToCrewScheduleDtoConverter;
        this.crewScheduleCreateDtoToCrewScheduleConverter = crewScheduleCreateDtoToCrewScheduleConverter;
        this.crewScheduleToCrewedUserDtoConverter = crewScheduleToCrewedUserDtoConverter;
    }

    @PostMapping("/{gameId}")
    public Result addCrewSchedule(@RequestBody List<@Valid CrewScheduleCreateDto> crewScheduleCreateDtos, @PathVariable int gameId) {
        List<CrewSchedule> crewSchedules = crewScheduleCreateDtoToCrewScheduleConverter.convert(crewScheduleCreateDtos);
        List<CrewSchedule> savedSchedules = new ArrayList<>();
        for (CrewSchedule crewSchedule : crewSchedules) {
            CrewSchedule newCrewSchedule = this.crewScheduleService.save(crewSchedule);
            savedSchedules.add(newCrewSchedule);
        }
        List<CrewedUser> crewMembers = new ArrayList<>();
        for (CrewSchedule schedule : savedSchedules) {
            CrewedUser crewMember = crewScheduleToCrewedUserDtoConverter.convert(schedule);
            crewMembers.add(crewMember);
        }
        return new Result(true, StatusCode.SUCCESS, "Add Success", crewMembers);
    }

    @GetMapping("/{gameId}")
    public Result getCrewScheduleByGame(@PathVariable int gameId) throws Exception {
        this.gameRepository.findById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("game", gameId));

        List<CrewSchedule> crewSchedules = this.crewScheduleService.findCrewScheduleByGameId(gameId);
        CrewScheduleDto crewScheduleDto = this.crewScheduleToCrewScheduleDtoConverter.convert(crewSchedules);
        return new Result(true, StatusCode.SUCCESS, "Find Success", crewScheduleDto);
    }

}
