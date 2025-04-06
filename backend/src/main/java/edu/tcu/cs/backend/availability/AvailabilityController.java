package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public Result addAvailability(@RequestBody Availability availability) {
        Availability savedAvailability = this.availabilityService.save(availability);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedAvailability);
    }

    @PutMapping
    public Result updateAvailability(@RequestBody Availability availability) {
        Availability oldAvailability = this.availabilityService.findById(availability.getId());

        oldAvailability.setUser(availability.getUser());
        oldAvailability.setGame(availability.getGame());

        this.availabilityService.save(oldAvailability);

        return new Result(true, StatusCode.SUCCESS, "Update Availability Success", oldAvailability);
    }

    @GetMapping("/{gameId}")
    public Result getAvailabilityByGame(@PathVariable int gameId) {
        List<Availability> availability = this.availabilityService.findAvailabilityByGameId(gameId);
        return new Result(true, StatusCode.SUCCESS, "Find By Game Success", availability);
    }
}
