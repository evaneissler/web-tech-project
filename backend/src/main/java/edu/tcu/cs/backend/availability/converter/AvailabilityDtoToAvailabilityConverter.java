package edu.tcu.cs.backend.availability.converter;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.dto.AvailabilityDto;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.user.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDtoToAvailabilityConverter implements Converter<AvailabilityDto, Availability> {

    private final UserService userService;
    private final GameService gameService;

    public AvailabilityDtoToAvailabilityConverter(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public Availability convert(AvailabilityDto source) {
        Availability availability = new Availability();
        availability.setUser(userService.findById(source.userId()));
        availability.setGame(gameService.findById(source.gameId()));
        availability.setAvailability(source.availability());
        availability.setComment(source.comment());
        return availability;
    }
}

