package edu.tcu.cs.backend.user.invitedUser;

import edu.tcu.cs.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitedUserRepository extends JpaRepository<InvitedUser, Integer> {
    Optional<InvitedUser> findByEmail(String email);
}
