package edu.tcu.cs.backend.game.dto;

public record GameDto(Integer gameId,
                              Integer scheduleId,
                              String gameDate,
                              String venue,
                              String opponent,
                              Boolean isFinalized) {
}
