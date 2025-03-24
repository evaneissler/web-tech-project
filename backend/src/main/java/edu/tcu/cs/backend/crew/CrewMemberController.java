package edu.tcu.cs.backend.crew;

import edu.tcu.cs.backend.admin.Admin;
import edu.tcu.cs.backend.admin.AdminService;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crew-members")
public class CrewMemberController {
    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping
    public Result findAllCrewMembers() {
        List<CrewMember> foundCrewMembers = this.crewMemberService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundCrewMembers);
    }
}
