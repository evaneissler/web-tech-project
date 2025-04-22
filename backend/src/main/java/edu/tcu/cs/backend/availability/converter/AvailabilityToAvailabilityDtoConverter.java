package edu.tcu.cs.backend.availability.converter;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.availability.dto.AvailabilityDto;
import edu.tcu.cs.backend.game.GameService;
import edu.tcu.cs.backend.game.dto.GameDto;
import edu.tcu.cs.backend.user.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityToAvailabilityDtoConverter implements Converter<Availability, AvailabilityDto> {

    @Override
    public AvailabilityDto convert(Availability source) {
        AvailabilityDto availabilityDto = new AvailabilityDto(
                source.getUser().getId(),
                source.getGame().getId(),
                source.getAvailability(),
                source.getComment()
        );
        return availabilityDto;
    }
}