package edu.tcu.cs.backend.gameSchedule.converter;

import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import edu.tcu.cs.backend.gameSchedule.dto.GameScheduleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameScheduleToGameScheduleDtoConverter implements Converter<GameSchedule, GameScheduleDto> {

    @Override
    public GameScheduleDto convert(GameSchedule source) {
        final GameScheduleDto gameScheduleDto = new GameScheduleDto(source.getId(),
                source.getSport(),
                source.getSeason()
        );
        return gameScheduleDto;
    }

}
