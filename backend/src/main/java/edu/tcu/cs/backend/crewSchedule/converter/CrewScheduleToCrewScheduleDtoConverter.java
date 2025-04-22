package edu.tcu.cs.backend.crewSchedule.converter;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.dto.AvailabilityDto;
import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.dto.CrewScheduleDto;
import edu.tcu.cs.backend.crewSchedule.dto.CrewedUser;
import edu.tcu.cs.backend.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.converter.UserToCrewMemberDtoConverter;
import edu.tcu.cs.backend.user.converter.UserToUserDtoConverter;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrewScheduleToCrewScheduleDtoConverter {

    private final CrewScheduleToCrewedUserDtoConverter crewToCrewedUserDtoConverter;

    public CrewScheduleToCrewScheduleDtoConverter(CrewScheduleToCrewedUserDtoConverter crewToCrewedUserDtoConverter) {
        this.crewToCrewedUserDtoConverter = crewToCrewedUserDtoConverter;
    }

    public CrewScheduleDto convert(List<CrewSchedule> source) {
        List<CrewedUser> crewMembers = new ArrayList<>();
        for (CrewSchedule schedule : source) {
            CrewedUser crewMember = crewToCrewedUserDtoConverter.convert(schedule);
            crewMembers.add(crewMember);
        }
        CrewScheduleDto crewScheduleDto = new CrewScheduleDto(
                source.get(0).getGame().getId(),
                source.get(0).getGame().getGameStart(),
                source.get(0).getGame().getGameDate(),
                source.get(0).getGame().getVenue(),
                source.get(0).getGame().getOpponent(),
                crewMembers
        );
        return crewScheduleDto;
    }
}