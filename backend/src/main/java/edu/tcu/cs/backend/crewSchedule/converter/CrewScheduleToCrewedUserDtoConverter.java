package edu.tcu.cs.backend.crewSchedule.converter;

import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewedUser;
import edu.tcu.cs.backend.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.backend.user.converter.UserToCrewMemberDtoConverter;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrewScheduleToCrewedUserDtoConverter {

    public CrewedUser convert(CrewSchedule source) {
        CrewedUser crewedUser = new CrewedUser(
                source.getCrewedUserId(),
                source.getUser().getId(),
                source.getGame().getId(),
                source.getPosition(),
                source.getUser().getFirstName() + " " + source.getUser().getLastName(),
                source.getReportTime(),
                source.getReportLocation()
        );
        return crewedUser;
    }
}