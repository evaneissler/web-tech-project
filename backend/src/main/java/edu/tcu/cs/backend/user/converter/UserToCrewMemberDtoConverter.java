package edu.tcu.cs.backend.user.converter;

import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import edu.tcu.cs.backend.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToCrewMemberDtoConverter implements Converter<User, CrewMemberDto> {

    @Override
    public CrewMemberDto convert(User source) {
        final CrewMemberDto crewMemberDto = new CrewMemberDto(source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getPhoneNumber(),
                source.getRole(),
                source.getPositions()
        );
        return crewMemberDto;
    }

}