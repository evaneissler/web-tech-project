package edu.tcu.cs.backend.user.dto;

import java.util.List;

public record CrewMemberDto(Integer id,
                            String firstName,
                            String lastName,
                            String email,
                            String phoneNumber,
                            String role,
                            List<String> positions) {
}
