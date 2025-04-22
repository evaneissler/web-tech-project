package edu.tcu.cs.backend.availability.dto;

import jakarta.validation.constraints.NotNull;

public record AvailabilityDto(
        @NotNull(message = "User id is required") Integer userId,
        @NotNull(message = "Game id is required") Integer gameId,
        @NotNull(message = "Availability is required") Integer availability,
        String comment) {
}