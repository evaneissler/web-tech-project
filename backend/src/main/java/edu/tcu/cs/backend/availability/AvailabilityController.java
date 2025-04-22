package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.availability.converter.AvailabilityDtoToAvailabilityConverter;
import edu.tcu.cs.backend.availability.converter.AvailabilityToAvailabilityDtoConverter;
import edu.tcu.cs.backend.availability.dto.AvailabilityDto;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final AvailabilityDtoToAvailabilityConverter availabilityDtoToAvailabilityConverter;
    private final AvailabilityToAvailabilityDtoConverter availabilityToAvailabilityDtoConverter;

    public AvailabilityController(AvailabilityService availabilityService, AvailabilityDtoToAvailabilityConverter availabilityDtoToAvailabilityConverter, AvailabilityToAvailabilityDtoConverter availabilityToAvailabilityDtoConverter) {
        this.availabilityService = availabilityService;
        this.availabilityDtoToAvailabilityConverter = availabilityDtoToAvailabilityConverter;
        this.availabilityToAvailabilityDtoConverter = availabilityToAvailabilityDtoConverter;
    }

    @PostMapping
    public Result addAvailability(@Valid @RequestBody AvailabilityDto availabilityDto) {
        Availability availability = availabilityDtoToAvailabilityConverter.convert(availabilityDto);
        Availability savedAvailability = this.availabilityService.save(availability);
        AvailabilityDto availabilitySavedDto = availabilityToAvailabilityDtoConverter.convert(savedAvailability);
        return new Result(true, StatusCode.SUCCESS, "Add Success", availabilitySavedDto);
    }

    @PutMapping
    public Result updateAvailability(@RequestBody Availability availability) {
        Availability oldAvailability = this.availabilityService.findById(availability.getId());

        oldAvailability.setUser(availability.getUser());
        oldAvailability.setGame(availability.getGame());

        this.availabilityService.save(oldAvailability);

        AvailabilityDto updatedAvailability = availabilityToAvailabilityDtoConverter.convert(oldAvailability);

        return new Result(true, StatusCode.SUCCESS, "Update Availability Success", updatedAvailability);
    }

    @GetMapping("/{gameId}")
    public Result getAvailabilityByGame(@PathVariable int gameId) {
        List<Availability> availabilities = this.availabilityService.findAvailabilityByGameId(gameId);
        List<AvailabilityDto> availabilityDtos = new ArrayList<>();
        for (Availability availability : availabilities) {
            availabilityDtos.add(availabilityToAvailabilityDtoConverter.convert(availability));
        }
        return new Result(true, StatusCode.SUCCESS, "Find By Game Success", availabilityDtos);
    }
}
