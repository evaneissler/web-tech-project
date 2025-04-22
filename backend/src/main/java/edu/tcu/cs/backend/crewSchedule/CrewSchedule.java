package edu.tcu.cs.backend.crewSchedule;


import edu.tcu.cs.backend.game.Game;
import edu.tcu.cs.backend.user.User;
import jakarta.persistence.*;

@Entity
public class CrewSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewedUserId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String position;
    private String reportTime = "";
    private String reportLocation = "CONTROL ROOM";

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
        return crewedUserId;
    }

    public void setId(int i) {
        this.crewedUserId = i;
    }

    public Integer getCrewedUserId() {
        return this.crewedUserId;
    }

    public void setCrewedUserId(Integer crewedUserId) {
        this.crewedUserId = crewedUserId;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }
}

