package edu.tcu.cs.backend.user.converter;

import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        final UserDto userDto = new UserDto(source.getId(),
                source.getFirstName() + " " + source.getLastName(),
                source.getEmail(),
                source.getPhoneNumber());
        return userDto;
    }

}