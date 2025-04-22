package edu.tcu.cs.backend.crewSchedule.dto;

public record CrewedUser(Integer crewedUserId,
                                    Integer userId,
                                    Integer gameId,
                                    String position,
                                    String fullName,
                                    String reportTime,
                                    String reportLocation) {
}