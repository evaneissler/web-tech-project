package edu.tcu.cs.backend.system;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.AvailabilityRepository;
import edu.tcu.cs.backend.crewSchedule.CrewSchedule;
import edu.tcu.cs.backend.crewSchedule.CrewScheduleRepository;
import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.GameRepository;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.GameScheduleRepository;
import edu.tcu.cs.backend.gameSchedule.GameScheduleService;
import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import edu.tcu.cs.backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final GameScheduleRepository gameScheduleRepository;
    private final GameRepository gameRepository;
    private final AvailabilityRepository availabilityRepository;
    private final CrewScheduleRepository crewScheduleRepository;

    public DBDataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, GameScheduleRepository gameScheduleRepository, GameRepository gameRepository, AvailabilityRepository availabilityRepository, CrewScheduleRepository crewScheduleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.gameScheduleRepository = gameScheduleRepository;
        this.gameRepository = gameRepository;
        this.availabilityRepository = availabilityRepository;
        this.crewScheduleRepository = crewScheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setRole("user");
        user1.addPosition("Director");
        user1.addPosition("Producer");
        user1.addPosition("Manager");
        user1.removePosition("Manager");
        this.userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane@smith.com");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setRole("admin");
        user2.addPosition("Camera Operator");
        this.userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("Jack");
        user3.setLastName("Brown");
        user3.setEmail("jack@brown.com");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setRole("user");
        this.userRepository.save(user3);

        GameSchedule schedule1 = new GameSchedule();
        schedule1.setName("Football Schedule");

        GameSchedule schedule2 = new GameSchedule();
        schedule2.setName("Basketball Schedule");

        GameSchedule schedule3 = new GameSchedule();
        schedule3.setName("Baseball Schedule");

        gameScheduleRepository.save(schedule1);
        gameScheduleRepository.save(schedule2);
        gameScheduleRepository.save(schedule3);

        Game game1 = new Game();
        game1.setName("Football Game");
        game1.setGameSchedule(schedule1);

        Game game2 = new Game();
        game2.setName("Baseball Game");
        game2.setGameSchedule(schedule1);

        Game game3 = new Game();
        game3.setName("Basketball Game");
        game3.setGameSchedule(schedule1);

        Game game4 = new Game();
        game4.setName("Soccer Game");
        game4.setGameSchedule(schedule1);

        Game game5 = new Game();
        game5.setName("Football Game");
        game5.setGameSchedule(schedule2);

        Game game6 = new Game();
        game6.setName("Football Game");
        game6.setGameSchedule(schedule2);

        Availability availability1 = new Availability();
        availability1.setGame(game3);
        availability1.setUser(user1);

        Availability availability2 = new Availability();
        availability2.setGame(game3);
        availability2.setUser(user3);

        Availability availability3 = new Availability();
        availability3.setGame(game6);
        availability3.setUser(user3);

        CrewSchedule crewSchedule1 = new CrewSchedule();
        crewSchedule1.setGame(game1);
        crewSchedule1.setUser(user1);

        CrewSchedule crewSchedule2 = new CrewSchedule();
        crewSchedule2.setGame(game2);
        crewSchedule2.setUser(user1);

        CrewSchedule crewSchedule3 = new CrewSchedule();
        crewSchedule3.setGame(game2);
        crewSchedule3.setUser(user2);

        CrewSchedule crewSchedule4 = new CrewSchedule();
        crewSchedule4.setGame(game2);
        crewSchedule4.setUser(user3);

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        gameRepository.save(game5);
        gameRepository.save(game6);

        availabilityRepository.save(availability1);
        availabilityRepository.save(availability2);
        availabilityRepository.save(availability3);

        crewScheduleRepository.save(crewSchedule1);
        crewScheduleRepository.save(crewSchedule2);
        crewScheduleRepository.save(crewSchedule3);
        crewScheduleRepository.save(crewSchedule4);

    }
}
