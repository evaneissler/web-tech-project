package edu.tcu.cs.backend.system;

import edu.tcu.cs.backend.admin.Admin;
import edu.tcu.cs.backend.admin.AdminRepository;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DBDataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");
        user1.setPassword("password");
        user1.setRole("USER");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane@smith.com");
        user2.setPassword("password");
        user2.setRole("ADMIN");
        userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("Jack");
        user3.setLastName("Brown");
        user3.setEmail("jack@brown.com");
        user3.setPassword("password");
        user3.setRole("USER");
        userRepository.save(user3);

    }
}
