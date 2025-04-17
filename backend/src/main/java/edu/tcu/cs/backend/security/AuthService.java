package edu.tcu.cs.backend.security;


import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import edu.tcu.cs.backend.user.converter.UserToUserDtoConverter;
import edu.tcu.cs.backend.user.dto.UserDto;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        User user = principal.getUser();
//        UserDto userDto = this.userToUserDtoConverter.convert(user);

        // Create JWT
        String token = this.jwtProvider.createToken(authentication);
        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", user);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}

