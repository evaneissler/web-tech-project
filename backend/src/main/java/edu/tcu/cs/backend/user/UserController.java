package edu.tcu.cs.backend.user;

import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crewMember")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Result findAllCrewMembers() {
        List<User> foundUsers = this.userService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundUsers);
    }

    @PostMapping
    public Result addUser(@RequestBody User newHogwartsUser) {
        User savedUser = this.userService.save(newHogwartsUser);
        //UserDto savedUserDto = this.userToUserDtoConverter.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUser);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) {
        User foundUser = this.userService.findById(id);
        return new Result(true, StatusCode.SUCCESS, "Find Success", foundUser);
    }
}
