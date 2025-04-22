package edu.tcu.cs.backend.crewSchedule.dto;

import edu.tcu.cs.backend.game.dto.GameDto;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;

import java.util.List;

public record CrewScheduleDto(Integer gameId,
                              String gameStart,
                              String gameDate,
                              String venue,
                              String opponent,
                              List<CrewedUser> crewedMembers) {
}