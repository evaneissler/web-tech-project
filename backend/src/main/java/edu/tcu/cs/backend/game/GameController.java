package edu.tcu.cs.backend.game;

import edu.tcu.cs.backend.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.backend.game.dto.GameDto;
import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing game operations.
 * Provides endpoints for retrieving, updating, and finding games within schedules.
 * All endpoints are prefixed with "/api/v1/gameSchedule".
 */
@RestController
@RequestMapping("/api/v1/gameSchedule")
public class GameController {

    private final GameService gameService;
    private final GameToGameDtoConverter gameToGameDtoConverter;

    /**
     * Constructor for dependency injection.
     * @param gameService Service layer for game business logic.
     * @param gameToGameDtoConverter Converts Game entities to GameDto objects.
     */
    public GameController(GameService gameService, GameToGameDtoConverter gameToGameDtoConverter) {
        this.gameService = gameService;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
    }

    /**
     * Retrieves all games.
     * @return Result object containing list of all GameDto objects.
     */
    @GetMapping("/games")
    public Result findAllGames() {
        // Fetch all games from service layer
        List<Game> foundGames = this.gameService.findAll();
        
        // Convert each Game entity to GameDto for API response
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : foundGames) {
            GameDto gameDto = this.gameToGameDtoConverter.convert(game);
            gameDtos.add(gameDto);
        }
        
        return new Result(true, StatusCode.SUCCESS, "Find All Success", gameDtos);
    }

    /**
     * Retrieves a specific game by its ID.
     * @param gameId The ID of the game to retrieve.
     * @return Result object containing the GameDto for the requested game.
     */
    @GetMapping("/game/{gameId}")
    public Result findById(@PathVariable int gameId) {
        // Find game by ID, throws ObjectNotFoundException if not found
        Game found = this.gameService.findById(gameId);
        
        // Convert to DTO for response
        GameDto gameDto = this.gameToGameDtoConverter.convert(found);
        return new Result(true, StatusCode.SUCCESS, "Find Success", gameDto);
    }

    /**
     * Updates an existing game.
     * @param gameId The ID of the game to update.
     * @param updatedGame Game object containing updated fields from request body.
     * @return Result object containing the updated GameDto.
     */
    @PutMapping("/game/{gameId}")
    public Result updateGame(@PathVariable int gameId, @RequestBody Game updatedGame) {
        // Retrieve existing game
        Game oldGame = this.gameService.findById(gameId);
        
        // Update fields from the request
        oldGame.setGameDate(updatedGame.getGameDate());
        oldGame.setVenue(updatedGame.getVenue());
        oldGame.setOpponent(updatedGame.getOpponent());
        oldGame.setIsFinalized(updatedGame.getIsFinalized());
        
        // Save updated game, preserving its schedule association
        Game saved = this.gameService.save(oldGame, oldGame.getGameSchedule().getId());
        
        // Convert to DTO for response
        GameDto gameDto = this.gameToGameDtoConverter.convert(saved);
        return new Result(true, StatusCode.SUCCESS, "Update Success", gameDto);
    }

    /**
     * Retrieves all games belonging to a specific schedule.
     * @param scheduleId The ID of the schedule to filter games by.
     * @return Result object containing list of GameDto objects for the schedule.
     */
    @GetMapping("/{scheduleId}/games")
    public Result findGamesBySchedule(@PathVariable int scheduleId) {
        // Find all games associated with the given schedule ID
        List<Game> foundGames = this.gameService.findAllByScheduleId(scheduleId);
        
        // Convert each Game entity to GameDto
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : foundGames) {
            GameDto gameDto = this.gameToGameDtoConverter.convert(game);
            gameDtos.add(gameDto);
        }
        
        return new Result(true, StatusCode.SUCCESS, "Find By Schedule Success", gameDtos);
    }
}
