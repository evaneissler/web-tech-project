package edu.tcu.cs.backend.game.converter;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.game.dto.GameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameToGameDtoConverter implements Converter<Game, GameDto> {

    @Override
    public GameDto convert(Game source) {
        final GameDto gameDto = new GameDto(source.getId(),
                source.getGameSchedule().getId(),
                source.getGameDate(),
                source.getVenue(),
                source.getOpponent(),
                source.getIsFinalized()
        );
        return gameDto;
    }

}
