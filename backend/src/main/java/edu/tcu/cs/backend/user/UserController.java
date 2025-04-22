package edu.tcu.cs.backend.user;

import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import edu.tcu.cs.backend.user.converter.UserToCrewMemberDtoConverter;
import edu.tcu.cs.backend.user.converter.UserToUserDtoConverter;
import edu.tcu.cs.backend.user.dto.CrewMemberDto;
import edu.tcu.cs.backend.user.dto.UserDto;
import edu.tcu.cs.backend.user.invitedUser.InviteRequest;
import edu.tcu.cs.backend.user.invitedUser.InvitedUser;
import edu.tcu.cs.backend.user.invitedUser.InvitedUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserToCrewMemberDtoConverter userToCrewMemberDtoConverter;
    private final InvitedUserService invitedUserService;

    public UserController(UserService userService, UserToUserDtoConverter userToUserDtoConverter, UserToCrewMemberDtoConverter userToCrewMemberDtoConverter, InvitedUserService invitedUserService) {
        this.userService = userService;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userToCrewMemberDtoConverter = userToCrewMemberDtoConverter;
        this.invitedUserService = invitedUserService;
    }

    @GetMapping("/crewMember")
    public Result findAllCrewMembers() {
        List<User> foundUsers = this.userService.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : foundUsers) {
            UserDto dto = userToUserDtoConverter.convert(user);
            userDtos.add(dto);
        }
        return new Result(true, StatusCode.SUCCESS, "Find Success", userDtos);
    }

    @PostMapping("/crewMember")
    public Result addUser(@Valid @RequestBody User newUser) {
        User savedUser = this.userService.save(newUser);
        CrewMemberDto crewMemberDto = userToCrewMemberDtoConverter.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", crewMemberDto);
    }

    @GetMapping("/crewMember/{id}")
    public Result findById(@PathVariable int id) {
        User foundUser = this.userService.findById(id);
        CrewMemberDto crewMemberDto = userToCrewMemberDtoConverter.convert(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find Success", crewMemberDto);
    }

    @PutMapping("/crewMember/disable/{id}")
    public Result disableUser(@PathVariable int id) {
        User foundUser = this.userService.findById(id);
        foundUser.setEnabled(false);
        this.userService.save(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Disable Success", null);
    }

    @PostMapping("/invite")
    public Result inviteUser(@RequestBody InviteRequest inviteRequest) {
        List<String> InvitedEmails = new ArrayList<>();

        for (String email : inviteRequest.getEmails()) {
            InvitedUser invitedUser = new InvitedUser();
            invitedUser.setEmail(email);

            InvitedUser invited = this.invitedUserService.save(invitedUser);
            InvitedEmails.add(invited.getEmail());
        }
        return new Result(true, StatusCode.SUCCESS, "Disable Success", InvitedEmails);
    }

}
