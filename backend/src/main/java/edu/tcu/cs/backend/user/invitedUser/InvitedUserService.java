package edu.tcu.cs.backend.user.invitedUser;

import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.backend.user.MyUserPrincipal;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvitedUserService {
    private final InvitedUserRepository invitedUserRepository;

    public InvitedUserService(InvitedUserRepository invitedUserRepository) {
        this.invitedUserRepository = invitedUserRepository;
    }

    public InvitedUser save(InvitedUser newUser) {
        return this.invitedUserRepository.save(newUser);
    }

    public InvitedUser findByEmail(String email) throws UsernameNotFoundException {
        return this.invitedUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invitation Not Valid"));
    }
}
