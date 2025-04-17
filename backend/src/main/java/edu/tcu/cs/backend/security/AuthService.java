package edu.tcu.cs.backend.security;


import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    public AuthService(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        User user = principal.getUser();

        // Create JWT
        String token = this.jwtProvider.createToken(authentication);
        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", user);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}

