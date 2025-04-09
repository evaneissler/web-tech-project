package edu.tcu.cs.backend.user;

import edu.tcu.cs.backend.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User newUser) {
//        newUser.setPassword(newUser.getPassword());
        return this.userRepository.save(newUser);
    }

    public User findById(int id) throws ObjectNotFoundException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
    }

    public List<User> findByPosition(String position) throws ObjectNotFoundException {
        return this.userRepository.findAll().stream()
                .filter(user -> user.getPositions() != null && user.getPositions().contains(position))
                .collect(Collectors.toList());
    }
}
