package edu.tcu.cs.backend.gameSchedule;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.tcu.cs.backend.game.Game;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GameSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "gameSchedule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Game> games = new ArrayList<>();

    private String sport;
    private String season;

    public GameSchedule() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getSport() { return this.sport; }

    public void setSport(String sport) { this.sport = sport; }

    public String getSeason() { return this.season; }

    public void setSeason(String season) { this.season = season; }

    public List<Game> getGames() { return this.games; }

    public void addGame(Game game) {
        this.games.add(game);
        game.setGameSchedule(this);
    }

}
