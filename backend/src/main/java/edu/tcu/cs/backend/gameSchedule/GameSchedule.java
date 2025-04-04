package edu.tcu.cs.backend.gameSchedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GameSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public GameSchedule() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}
