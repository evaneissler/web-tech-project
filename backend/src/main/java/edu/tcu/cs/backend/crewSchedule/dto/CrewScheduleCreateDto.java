package edu.tcu.cs.backend.crewSchedule.dto;

import edu.tcu.cs.backend.game.dto.GameDto;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CrewScheduleCreateDto(Integer crewedUserId,

                                    @NotNull(message = "UserId is required")
                                    Integer userId,

                                    Integer gameId,

                                    @NotNull(message = "Position is required")
                                    String position) {
}