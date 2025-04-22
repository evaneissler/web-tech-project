package edu.tcu.cs.backend.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_schedule_id", referencedColumnName = "id")
    @JsonBackReference
    private GameSchedule gameSchedule;

    private String gameStart;

    @NotNull(message = "Game date is required")
    private String gameDate;

    @NotNull(message = "Venue is required")
    private String venue;

    @NotNull(message = "Opponent is required")
    private String opponent;

    @NotNull(message = "Is finalized is required")
    private Boolean isFinalized;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    public Game() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getGameDate() {
        return this.gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getOpponent() {
        return this.opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public Boolean getIsFinalized() {
        return this.isFinalized;
    }

    public void setIsFinalized(Boolean isFinalized) {
        this.isFinalized = isFinalized;
    }

    public GameSchedule getGameSchedule() { return gameSchedule; }

    public void setGameSchedule(GameSchedule gameSchedule) { this.gameSchedule = gameSchedule; }

    public String getGameStart() {
        return gameStart;
    }

    public void setGameStart(String gameStart) {
        this.gameStart = gameStart;
    }
}
