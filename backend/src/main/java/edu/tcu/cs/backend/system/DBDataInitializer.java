package edu.tcu.cs.backend.system;

import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleRepository;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final GameScheduleRepository gameScheduleRepository;

    public DBDataInitializer(UserRepository userRepository, GameScheduleRepository gameScheduleRepository) {
        this.userRepository = userRepository;
        this.gameScheduleRepository = gameScheduleRepository;
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

        GameSchedule schedule1 = new GameSchedule();
        schedule1.setName("Football Schedule");

        GameSchedule schedule2 = new GameSchedule();
        schedule2.setName("Basketball Schedule");

        GameSchedule schedule3 = new GameSchedule();
        schedule3.setName("Baseball Schedule");

        gameScheduleRepository.save(schedule1);
        gameScheduleRepository.save(schedule2);
        gameScheduleRepository.save(schedule3);
        
    }
}
