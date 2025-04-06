package edu.tcu.cs.backend.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.gameSchedule.GameSchedule;
import jakarta.persistence.*;

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

    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    public Game() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public GameSchedule getGameSchedule() { return gameSchedule; }

    public void setGameSchedule(GameSchedule gameSchedule) { this.gameSchedule = gameSchedule; }
}
