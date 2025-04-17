package edu.tcu.cs.backend.user.converter;

import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto source) {
        User user = new User();
        user.setEmail(source.username());
        user.setEnabled(source.enabled());
        user.setRole(source.roles());
        return user;
    }

}