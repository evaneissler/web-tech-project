package edu.tcu.cs.backend.user.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(Integer id,
                      String fullName,
                      String email,
                      String phoneNumber) {
}
