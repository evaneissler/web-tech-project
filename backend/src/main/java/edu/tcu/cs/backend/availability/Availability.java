package edu.tcu.cs.backend.availability;

import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.user.User;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer availability;
    private String comment;

    public Availability() {}

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

    public Integer getAvailability() {
        return this.availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
