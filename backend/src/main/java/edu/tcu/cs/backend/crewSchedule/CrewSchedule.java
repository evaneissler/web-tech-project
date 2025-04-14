package edu.tcu.cs.backend.crewSchedule;


import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.user.User;
import jakarta.persistence.*;

@Entity
public class CrewSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public CrewSchedule() {}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }
}

